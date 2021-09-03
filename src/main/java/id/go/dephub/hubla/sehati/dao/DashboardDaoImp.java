package id.go.dephub.hubla.sehati.dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Repository;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import id.go.dephub.hubla.sehati.mapper.DashboardKeyValueMapper;
import id.go.dephub.hubla.sehati.mapper.DashboardPelabuhanMapper;
import id.go.dephub.hubla.sehati.model.DashboardKeyValue;
import id.go.dephub.hubla.sehati.model.DashboardPelabuhan;

@Repository
public class DashboardDaoImp implements DashboardDao{
	@Autowired
	@Qualifier("portalJdbcTemplate")
	private JdbcTemplate portalTemplate;
	
	@Autowired
	@Qualifier("datalakeJdbcTemplate")
	private JdbcTemplate datalakeTemplate;

	@Autowired
	@Qualifier("datalakeOldJdbcTemplate")
	private JdbcTemplate datalakeOldTemplate;

	@Autowired
	@Qualifier("workflowJdbcTemplate")
	private JdbcTemplate workflowTemplate;

	@Autowired
	HomeDao home;	

	@SuppressWarnings("deprecation")
	@Override
	public String getDsbPelabuhanJml(HttpServletRequest request) {
		String what = request.getParameter("what");
		int jumlah  = 0;
		if(what==null || what.length()<1) {
	        String sql = "select count(*) jumlah "
		   		   	   + "from gold.m_pelabuhan a "
		   		   	   + "where a.kode_pelabuhan is not null ";
			jumlah = datalakeTemplate.queryForObject(sql, Integer.class);			
		}else {
			if(what.equals("satker")) {
		        String sql = "select count(*) jumlah "
			   		   	   + "from gold.m_pelabuhan a "
			   		   	   + "where a.kode_pelabuhan is not null and a.kode_upt=?";
				jumlah = datalakeTemplate.queryForObject(sql, new Object[]{request.getParameter("key")}, Integer.class);							
			}
			if(what.equals("propinsi")) {
		        String sql = "select count(*) jumlah "
			   		   	   + "from gold.m_pelabuhan a "
						   + "left join gold.m_regional e on a.id_kabkota = e.id_kemendagri "
						   + "left join gold.m_regional f on e.fk_parent_id = f.id_kemendagri "
			   		   	   + "where a.kode_pelabuhan is not null and f.nama = ?";
				jumlah = datalakeTemplate.queryForObject(sql, new Object[]{request.getParameter("key")}, Integer.class);							
			}
		}
		return String.valueOf(jumlah);
	}

	@Override
	public List<DashboardKeyValue> getDsbPelabuhanSatker(HttpServletRequest request) {
		
		String what = request.getParameter("what");
		if(what==null || what.length()<1) {
			String sql  = "select a.kode_upt kode, c.nama_jenis_singkat||' '||d.nama_kelas||' '||b.upt keterangan, count(*) nilai "
						+ "from gold.m_pelabuhan a "
						+ "left join gold.m_satker b on a.kode_upt = b.kode_satker_kemenkeu "
						+ "left join gold.m_jenis_upt c on b.jenis_upt = c.id "
						+ "left join gold.m_kelas_satker d on b.kelas = d.id "
						+ "where a.kode_pelabuhan is not null "
						+ "group by a.kode_upt, c.nama_jenis_singkat||' '||d.nama_kelas||' '||b.upt "
						+ "order by 3 desc";
			List<DashboardKeyValue> rest = datalakeTemplate.query(sql, new DashboardKeyValueMapper());
		    return rest;
		}else {
			if(what.equals("propinsi")) {
				String sql  = "select a.kode_upt kode, c.nama_jenis_singkat||' '||d.nama_kelas||' '||b.upt keterangan, count(*) nilai "
						+ "from gold.m_pelabuhan a "
						+ "left join gold.m_satker b on a.kode_upt = b.kode_satker_kemenkeu "
						+ "left join gold.m_jenis_upt c on b.jenis_upt = c.id "
						+ "left join gold.m_kelas_satker d on b.kelas = d.id "
					    + "left join gold.m_regional e on a.id_kabkota = e.id_kemendagri "
					    + "left join gold.m_regional f on e.fk_parent_id = f.id_kemendagri "
						+ "where a.kode_pelabuhan is not null and f.nama = ? "
						+ "group by a.kode_upt, c.nama_jenis_singkat||' '||d.nama_kelas||' '||b.upt "
						+ "order by 3 desc";
				@SuppressWarnings("deprecation")
				List<DashboardKeyValue> rest = datalakeTemplate.query(sql, new Object[]{request.getParameter("key")}, new DashboardKeyValueMapper());
			    return rest;
			}else {
				return null;
			}
		}
	}

	@Override
	public List<DashboardKeyValue> getDsbPelabuhanPropinsi(HttpServletRequest request) {
		String sql  = "select kode_provinsi kode, nama_provinsi keterangan, count(*) nilai from dwh.pelabuhan "
					+ "group by kode_provinsi, nama_provinsi "
					+ "order by kode_provinsi";
		List<DashboardKeyValue> rest = datalakeTemplate.query(sql, new DashboardKeyValueMapper());
	    return rest;
	}

	@Override
	public List<DashboardPelabuhan> getDsbPelabuhanTable(HttpServletRequest request) {
		String what = request.getParameter("what");
		if(what==null || what.length()<1) {
			String sql  = "select a.kode_pelabuhan, a.nama_pelabuhan, a.koordinat_lintang, a.koordinat_bujur, "
						+ "c.nama_jenis_singkat||' '||d.nama_kelas||' '||b.upt satker, e.nama kabkota, f.nama propinsi, "
						+ "(select count(*) from gold.m_terminal g where g.kode_pelabuhan=a.kode_pelabuhan) jml_terminal, "
						+ "coalesce(min(h.kedalaman_min),0) kedalaman_min, coalesce(max(kedalaman_max),0) kedalaman_max, "
						+ "(select count(*) from gold.m_kapal_umum i where i.kode_pelabuhan_pendaftaran=a.kode_pelabuhan) jml_kapal "
						+ "from gold.m_pelabuhan a "
						+ "left join gold.m_satker b on a.kode_upt = b.kode_satker_kemenkeu "
						+ "left join gold.m_jenis_upt c on b.jenis_upt = c.id "
						+ "left join gold.m_kelas_satker d on b.kelas = d.id "
						+ "left join gold.m_regional e on a.id_kabkota = e.id_kemendagri "
						+ "left join gold.m_regional f on e.fk_parent_id = f.id_kemendagri "
						+ "left join gold.m_terminal h on h.kode_pelabuhan = a.kode_pelabuhan "
						+ "group by 1, 2, 3, 4, 5, 6, 7";
			List<DashboardPelabuhan> rest = datalakeTemplate.query(sql, new DashboardPelabuhanMapper());
		    return rest;			
		}else {
			if(what.equals("satker")) {
				String sql  = "select a.kode_pelabuhan, a.nama_pelabuhan, a.koordinat_lintang, a.koordinat_bujur, "
							+ "c.nama_jenis_singkat||' '||d.nama_kelas||' '||b.upt satker, e.nama kabkota, f.nama propinsi, "
							+ "(select count(*) from gold.m_terminal g where g.kode_pelabuhan=a.kode_pelabuhan) jml_terminal, "
							+ "coalesce(min(h.kedalaman_min),0) kedalaman_min, coalesce(max(kedalaman_max),0) kedalaman_max, "
							+ "(select count(*) from gold.m_kapal_umum i where i.kode_pelabuhan_pendaftaran=a.kode_pelabuhan) jml_kapal "
							+ "from gold.m_pelabuhan a "
							+ "left join gold.m_satker b on a.kode_upt = b.kode_satker_kemenkeu "
							+ "left join gold.m_jenis_upt c on b.jenis_upt = c.id "
							+ "left join gold.m_kelas_satker d on b.kelas = d.id "
							+ "left join gold.m_regional e on a.id_kabkota = e.id_kemendagri "
							+ "left join gold.m_regional f on e.fk_parent_id = f.id_kemendagri "
							+ "left join gold.m_terminal h on h.kode_pelabuhan = a.kode_pelabuhan "
							+ "where a.kode_upt=? "
							+ "group by 1, 2, 3, 4, 5, 6, 7";
				@SuppressWarnings("deprecation")
				List<DashboardPelabuhan> rest = datalakeTemplate.query(sql, new Object[]{request.getParameter("key")}, new DashboardPelabuhanMapper());
			    return rest;							
			}else {
				if(what.equals("propinsi")) {
					String sql  = "select a.kode_pelabuhan, a.nama_pelabuhan, a.koordinat_lintang, a.koordinat_bujur, "
								+ "c.nama_jenis_singkat||' '||d.nama_kelas||' '||b.upt satker, e.nama kabkota, f.nama propinsi, "
								+ "(select count(*) from gold.m_terminal g where g.kode_pelabuhan=a.kode_pelabuhan) jml_terminal, "
								+ "coalesce(min(h.kedalaman_min),0) kedalaman_min, coalesce(max(kedalaman_max),0) kedalaman_max, "
								+ "(select count(*) from gold.m_kapal_umum i where i.kode_pelabuhan_pendaftaran=a.kode_pelabuhan) jml_kapal "
								+ "from gold.m_pelabuhan a "
								+ "left join gold.m_satker b on a.kode_upt = b.kode_satker_kemenkeu "
								+ "left join gold.m_jenis_upt c on b.jenis_upt = c.id "
								+ "left join gold.m_kelas_satker d on b.kelas = d.id "
								+ "left join gold.m_regional e on a.id_kabkota = e.id_kemendagri "
								+ "left join gold.m_regional f on e.fk_parent_id = f.id_kemendagri "
								+ "left join gold.m_terminal h on h.kode_pelabuhan = a.kode_pelabuhan "
								+ "where f.nama=? "
								+ "group by 1, 2, 3, 4, 5, 6, 7";
					@SuppressWarnings("deprecation")
					List<DashboardPelabuhan> rest = datalakeTemplate.query(sql, new Object[]{request.getParameter("key")}, new DashboardPelabuhanMapper());
				    return rest;							
				}else {
					return null;
				}				
			}
		}
	}

	@Override
	public List<DashboardPelabuhan> getDsbPelabuhanKapal(HttpServletRequest request) {
		String what = request.getParameter("what");
		if(what==null || what.length()<1) {		
			String sql  = "select a.kode_pelabuhan, a.nama_pelabuhan, a.koordinat_lintang, a.koordinat_bujur, "
					+ "c.nama_jenis_singkat||' '||d.nama_kelas||' '||b.upt satker, e.nama kabkota, f.nama propinsi, "
					+ "(select count(*) from gold.m_terminal g where g.kode_pelabuhan=a.kode_pelabuhan) jml_terminal, "
					+ "coalesce(min(h.kedalaman_min),0) kedalaman_min, coalesce(max(kedalaman_max),0) kedalaman_max, "
					+ "(select count(*) from gold.m_kapal_umum i where i.kode_pelabuhan_pendaftaran=a.kode_pelabuhan) jml_kapal "
					+ "from gold.m_pelabuhan a "
					+ "left join gold.m_satker b on a.kode_upt = b.kode_satker_kemenkeu "
					+ "left join gold.m_jenis_upt c on b.jenis_upt = c.id "
					+ "left join gold.m_kelas_satker d on b.kelas = d.id "
					+ "left join gold.m_regional e on a.id_kabkota = e.id_kemendagri "
					+ "left join gold.m_regional f on e.fk_parent_id = f.id_kemendagri "
					+ "left join gold.m_terminal h on h.kode_pelabuhan = a.kode_pelabuhan "
					+ "group by 1, 2, 3, 4, 5, 6, 7 order by 11 desc limit 10";
			List<DashboardPelabuhan> rest = datalakeTemplate.query(sql, new DashboardPelabuhanMapper());
		    return rest;
		}else {
			if(what.equals("satker")) {
				String sql  = "select a.kode_pelabuhan, a.nama_pelabuhan, a.koordinat_lintang, a.koordinat_bujur, "
						+ "c.nama_jenis_singkat||' '||d.nama_kelas||' '||b.upt satker, e.nama kabkota, f.nama propinsi, "
						+ "(select count(*) from gold.m_terminal g where g.kode_pelabuhan=a.kode_pelabuhan) jml_terminal, "
						+ "coalesce(min(h.kedalaman_min),0) kedalaman_min, coalesce(max(kedalaman_max),0) kedalaman_max, "
						+ "(select count(*) from gold.m_kapal_umum i where i.kode_pelabuhan_pendaftaran=a.kode_pelabuhan) jml_kapal "
						+ "from gold.m_pelabuhan a "
						+ "left join gold.m_satker b on a.kode_upt = b.kode_satker_kemenkeu "
						+ "left join gold.m_jenis_upt c on b.jenis_upt = c.id "
						+ "left join gold.m_kelas_satker d on b.kelas = d.id "
						+ "left join gold.m_regional e on a.id_kabkota = e.id_kemendagri "
						+ "left join gold.m_regional f on e.fk_parent_id = f.id_kemendagri "
						+ "left join gold.m_terminal h on h.kode_pelabuhan = a.kode_pelabuhan "
						+ "where a.kode_upt=? "
						+ "group by 1, 2, 3, 4, 5, 6, 7 order by 11 desc limit 10";
				@SuppressWarnings("deprecation")
				List<DashboardPelabuhan> rest = datalakeTemplate.query(sql, new Object[]{request.getParameter("key")}, new DashboardPelabuhanMapper());
			    return rest;
			}else {
				if(what.equals("propinsi")) {
					String sql  = "select a.kode_pelabuhan, a.nama_pelabuhan, a.koordinat_lintang, a.koordinat_bujur, "
							+ "c.nama_jenis_singkat||' '||d.nama_kelas||' '||b.upt satker, e.nama kabkota, f.nama propinsi, "
							+ "(select count(*) from gold.m_terminal g where g.kode_pelabuhan=a.kode_pelabuhan) jml_terminal, "
							+ "coalesce(min(h.kedalaman_min),0) kedalaman_min, coalesce(max(kedalaman_max),0) kedalaman_max, "
							+ "(select count(*) from gold.m_kapal_umum i where i.kode_pelabuhan_pendaftaran=a.kode_pelabuhan) jml_kapal "
							+ "from gold.m_pelabuhan a "
							+ "left join gold.m_satker b on a.kode_upt = b.kode_satker_kemenkeu "
							+ "left join gold.m_jenis_upt c on b.jenis_upt = c.id "
							+ "left join gold.m_kelas_satker d on b.kelas = d.id "
							+ "left join gold.m_regional e on a.id_kabkota = e.id_kemendagri "
							+ "left join gold.m_regional f on e.fk_parent_id = f.id_kemendagri "
							+ "left join gold.m_terminal h on h.kode_pelabuhan = a.kode_pelabuhan "
							+ "where f.nama=? "
							+ "group by 1, 2, 3, 4, 5, 6, 7 order by 11 desc limit 10";
					@SuppressWarnings("deprecation")
					List<DashboardPelabuhan> rest = datalakeTemplate.query(sql, new Object[]{request.getParameter("key")}, new DashboardPelabuhanMapper());
				    return rest;
				}else {
					return null;
				}
			}
		}
	}

	@SuppressWarnings("deprecation")
	@Override
	public String getDsbKapalJml(HttpServletRequest request) {
		String what = request.getParameter("what");
		int jumlah_umum  = 0;
		int jumlah_negara  = 0;
		if(what==null || what.length()<1) {
	        String sql1 = "select count(*) jumlah "
		   		   	   + "from gold.m_kapal_umum a "
		   		   	   + "where a.jenis_pemilik in(1,2) ";
	        jumlah_umum = datalakeTemplate.queryForObject(sql1, Integer.class);			
	        
	        String sql2 = "select count(*) jumlah "
		   		   	   + "from gold.m_kapal_umum a "
		   		   	   + "where a.jenis_pemilik in(3) ";
	        jumlah_negara = datalakeTemplate.queryForObject(sql2, Integer.class);			
		}else {
			if(what.equals("satker")) {
		        String sql = "select count(*) jumlah "
			   		   	   + "from gold.m_pelabuhan a "
			   		   	   + "where a.kode_pelabuhan is not null and a.kode_upt=?";
		        jumlah_umum = datalakeTemplate.queryForObject(sql, new Object[]{request.getParameter("key")}, Integer.class);							
			}
			if(what.equals("propinsi")) {
		        String sql = "select count(*) jumlah "
			   		   	   + "from gold.m_pelabuhan a "
						   + "left join gold.m_regional e on a.id_kabkota = e.id_kemendagri "
						   + "left join gold.m_regional f on e.fk_parent_id = f.id_kemendagri "
			   		   	   + "where a.kode_pelabuhan is not null and f.nama = ?";
		        jumlah_umum = datalakeTemplate.queryForObject(sql, new Object[]{request.getParameter("key")}, Integer.class);							
			}
		}
		return "{\"umum\":\""+String.valueOf(jumlah_umum)+"\", \"negara\":\""+String.valueOf(jumlah_negara)+"\"}";
	}

	@Override
	public List<DashboardKeyValue> getDsbKapalBendera(HttpServletRequest request) {
		String what = request.getParameter("what");
		String key = request.getParameter("key");
		if(what==null || what.length()<1) {
			String sql  = "select a.bendera_asal kode, b.negara_nm keterangan, count(*) nilai "
						+ "from gold.m_kapal_umum a "
						+ "left join gold.m_negara b on a.bendera_asal = b.negara_id "
						+ "where a.bendera_asal is not null "
						+ "group by a.bendera_asal, b.negara_nm "
						+ "order by 3 desc";
			List<DashboardKeyValue> rest = datalakeTemplate.query(sql, new DashboardKeyValueMapper());
		    return rest;
		}else {
			if(what.equals("propinsi")) {
				String sql  = "select a.kode_upt kode, c.nama_jenis_singkat||' '||d.nama_kelas||' '||b.upt keterangan, count(*) nilai "
						+ "from gold.m_pelabuhan a "
						+ "left join gold.m_satker b on a.kode_upt = b.kode_satker_kemenkeu "
						+ "left join gold.m_jenis_upt c on b.jenis_upt = c.id "
						+ "left join gold.m_kelas_satker d on b.kelas = d.id "
					    + "left join gold.m_regional e on a.id_kabkota = e.id_kemendagri "
					    + "left join gold.m_regional f on e.fk_parent_id = f.id_kemendagri "
						+ "where a.kode_pelabuhan is not null and f.nama = ? "
						+ "group by a.kode_upt, c.nama_jenis_singkat||' '||d.nama_kelas||' '||b.upt "
						+ "order by 3 desc";
				@SuppressWarnings("deprecation")
				List<DashboardKeyValue> rest = datalakeTemplate.query(sql, new Object[]{request.getParameter("key")}, new DashboardKeyValueMapper());
			    return rest;
			}else {
				if(what.equals("pemilik")) {
					if(key.equals("1")) {
						String sql  = "select a.bendera_asal kode, b.negara_nm keterangan, count(*) nilai "
								+ "from gold.m_kapal_umum a "
								+ "left join gold.m_negara b on a.bendera_asal = b.negara_id "
								+ "where a.jenis_pemilik in(1,2) "
								+ "group by a.bendera_asal, b.negara_nm "
								+ "order by 3 desc";
						List<DashboardKeyValue> rest = datalakeTemplate.query(sql, new DashboardKeyValueMapper());
						return rest;
					}else {
						String sql  = "select a.bendera_asal kode, b.negara_nm keterangan, count(*) nilai "
								+ "from gold.m_kapal_umum a "
								+ "left join gold.m_negara b on a.bendera_asal = b.negara_id "
								+ "where a.jenis_pemilik=3 "
								+ "group by a.bendera_asal, b.negara_nm "
								+ "order by 3 desc";
						List<DashboardKeyValue> rest = datalakeTemplate.query(sql, new DashboardKeyValueMapper());
						return rest;
					}
				}else {
					return null;
				}
			}
		}
	}

	@Override
	public List<DashboardKeyValue> getDsbKapalTahun(HttpServletRequest request) {
		String sql  = "select substring(tahun_pembuatan from '(([0-9]+.*)*[0-9]+)') kode, substring(tahun_pembuatan from '(([0-9]+.*)*[0-9]+)') keterangan, count(*) nilai from gold.m_kapal_umum "+
					  "where substring(tahun_pembuatan from '(([0-9]+.*)*[0-9]+)') is not null "+
					  "and (substring(tahun_pembuatan from '(([0-9]+.*)*[0-9]+)')::integer>=(date_part('year', CURRENT_DATE)-10) and substring(tahun_pembuatan from '(([0-9]+.*)*[0-9]+)')::integer<=date_part('year', CURRENT_DATE)) "+
					  "group by substring(tahun_pembuatan from '(([0-9]+.*)*[0-9]+)') "+
					  "order by substring(tahun_pembuatan from '(([0-9]+.*)*[0-9]+)') asc";
		List<DashboardKeyValue> rest = datalakeTemplate.query(sql, new DashboardKeyValueMapper());
	    return rest;
	}

	@Override
	public List<DashboardKeyValue> getDsbKapalJenis(HttpServletRequest request) {
		String sql  = "select c.jenis_id kode, c.jenis_ket keterangan, count(*) nilai from gold.m_kapal_umum a "+
					"left join gold.m_daftar_jenisdetail_kapal b on a.jenis_kapal = b.jenisdetail_id "+
					"left join gold.m_daftar_jenis_kapal c on b.jenis_id = c.jenis_id "+
					"group by c.jenis_id, c.jenis_ket";
		List<DashboardKeyValue> rest = datalakeTemplate.query(sql, new DashboardKeyValueMapper());
		return rest;
	}

	@Override
	public List<DashboardKeyValue> getDsbKapalKategori(HttpServletRequest request) {
		String sql  = "select b.kategori_id kode, b.kategori_nm keterangan, count(*) nilai from gold.m_kapal_umum a "+
					"left join gold.m_daftar_kategori_kapal b on a.kategori_kapal = b.kategori_id "+
					"group by b.kategori_id, b.kategori_nm";
		List<DashboardKeyValue> rest = datalakeTemplate.query(sql, new DashboardKeyValueMapper());
		return rest;
	}

	@SuppressWarnings("deprecation")
	@Override
	public String getDsbLayananCounter(HttpServletRequest request) {
		String user_name = (String)request.getSession().getAttribute("sessionUserName");
		
		int counter_tugas  = 0;
		int counter_terbuka  = 0;
		int counter_selesai  = 0;
		
        String sql1 = "SELECT count(id) as jml " + 
		       		 "FROM public.ext_process_head " + 
		       		 "WHERE assignee = ? " + 
		       		 "AND process_state = 'ACTIVE' " + 
		       		 "AND task_active = true";
        counter_tugas = workflowTemplate.queryForObject(sql1, new Object[]{user_name}, Integer.class);			
        
        String sql2 = "SELECT count(id) as jml " + 
			       		 "FROM public.ext_process_head " + 
			       		 "WHERE assignee = ? " + 
			       		 "AND process_state = 'ACTIVE' " + 
			       		 "AND task_active = false";
        counter_terbuka = workflowTemplate.queryForObject(sql2, new Object[]{user_name}, Integer.class);	
        
        String sql3 = "SELECT count(id) as jml " + 
		       		 "FROM public.ext_process_head " + 
		       		 "WHERE assignee = ? " + 
		       		 "AND process_state = 'COMPLETED' " + 
		       		 "AND task_active = false";
        counter_selesai = workflowTemplate.queryForObject(sql3, new Object[]{user_name}, Integer.class);	
     
		return "{\"counter_tugas\":\""+String.valueOf(counter_tugas)+"\", \"counter_terbuka\":\""+String.valueOf(counter_terbuka)+"\", \"counter_selesai\":\""+String.valueOf(counter_selesai)+"\"}";
	}

	@SuppressWarnings("deprecation")
	@Override
	public String getDsbTersusJml(HttpServletRequest request) {
		String what = request.getParameter("what");
		int jumlah_tersus  = 0;
		int jumlah_tuks  = 0;
		if(what==null || what.length()<1) {
	        String sql1 = "select count(*) jumlah "
		   		   	   + "from portal.m_tersus_tuks a "
		   		   	   + "where TRIM(a.tersus_tuks)='TERSUS'";
	        jumlah_tersus = portalTemplate.queryForObject(sql1, Integer.class);			
	        
	        String sql2 = "select count(*) jumlah "
		   		   	   + "from portal.m_tersus_tuks a "
		   		   	   + "where TRIM(a.tersus_tuks)='TUKS'";
	        jumlah_tuks = portalTemplate.queryForObject(sql2, Integer.class);			
		}else {
			if(what.equals("satker")) {
		        String sql1 = "select count(*) jumlah "
			   		   	   + "from portal.m_tersus_tuks a "
			   		   	   + "where TRIM(a.tersus_tuks)='TERSUS' and a.ksop = ?";
		        jumlah_tersus = portalTemplate.queryForObject(sql1, new Object[]{request.getParameter("key")}, Integer.class);			
		        
		        String sql2 = "select count(*) jumlah "
			   		   	   + "from portal.m_tersus_tuks a "
			   		   	   + "where TRIM(a.tersus_tuks)='TUKS' and a.ksop = ?";
		        jumlah_tuks = portalTemplate.queryForObject(sql2, new Object[]{request.getParameter("key")}, Integer.class);							
			}
		}
		return "{\"tersus\":\""+String.valueOf(jumlah_tersus)+"\", \"tuks\":\""+String.valueOf(jumlah_tuks)+"\"}";
	}

	@Override
	public List<DashboardKeyValue> getDsbTersusSatker(HttpServletRequest request) {
		String what = request.getParameter("what");
		if(what==null || what.length()<1) {
			String sql  = "select b.kode_satker_kemenkeu kode, c.nama_jenis_singkat||' '||d.nama_kelas||' '||b.upt keterangan, count(*) nilai "
						+ "from portal.m_tersus_tuks a "
						+ "left join portal.m_satker b on a.ksop = b.kode_satker_kemenkeu "
						+ "left join portal.m_jenis_upt c on b.jenis_upt = c.id "
						+ "left join portal.m_kelas_satker d on b.kelas = d.id "
						+ "where a.id is not null "
						+ "group by b.kode_satker_kemenkeu, c.nama_jenis_singkat||' '||d.nama_kelas||' '||b.upt "
						+ "order by 3 desc";
			List<DashboardKeyValue> rest = portalTemplate.query(sql, new DashboardKeyValueMapper());
		    return rest;
		}else {
			if(what.equals("propinsi")) {
				String sql  = "select b.kode_satker_kemenkeu kode, c.nama_jenis_singkat||' '||d.nama_kelas||' '||b.upt keterangan, count(*) nilai "
						+ "from portal.m_tersus_tuks a "
						+ "left join portal.m_satker b on a.ksop = b.kode_satker_kemenkeu "
						+ "left join portal.m_jenis_upt c on b.jenis_upt = c.id "
						+ "left join portal.m_kelas_satker d on b.kelas = d.id "
						+ "where a.id is not null "
						+ "group by b.kode_satker_kemenkeu, c.nama_jenis_singkat||' '||d.nama_kelas||' '||b.upt "
						+ "order by 3 desc";
				@SuppressWarnings("deprecation")
				List<DashboardKeyValue> rest = datalakeTemplate.query(sql, new Object[]{request.getParameter("key")}, new DashboardKeyValueMapper());
			    return rest;
			}else {
				return null;
			}
		}
	}

	@Override
	public List<DashboardKeyValue> getDsbTersusPropinsi(HttpServletRequest request) {
		String sql  = "select b.id_kemendagri kode, b.nama keterangan, count(*) nilai "
					+ "from portal.m_tersus_tuks a "
					+ "left join portal.m_regional b on a.provinsi=b.id_kemendagri "
					+ "group by b.id_kemendagri, b.nama "
					+ "order by b.id_kemendagri";
	List<DashboardKeyValue> rest = portalTemplate.query(sql, new DashboardKeyValueMapper());
    return rest;
	}

	@Override
	public void snycTersus() {
		
		RestTemplate restTemplate 	= new RestTemplate();
		HttpHeaders headers 		= new HttpHeaders();
		headers.add("Authorization", "Basic c2ltcGVsZGVwaHViOnMxbXBlbHA0c3M=");
		
		HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(headers);
		ResponseEntity<String> response 				 = restTemplate.exchange("http://simpel.dephub.go.id/index.php/webservice/tersus/hU9o4SaibAJDnuURW2Px6SDw9kjv7d2q/sso", HttpMethod.POST, entity, String.class);
		String jsonData 		 						 = response.getBody();
		
		JSONArray jsonObj 	 = new JSONArray(jsonData);
		for(int i = 0; i<jsonObj.length(); i++) {
			
			JSONObject dataTersus = jsonObj.getJSONObject(i);
	        String sqlTersus   = "INSERT INTO portal.tm_tersustuks "
				        	   + "(penyelenggara, nama_provinsi, aktifitas, id, code, sk, lokasi_pelabuhan, koordinat, l_d, l_m, l_s, lintang, b_d, b_m, b_s, bujur, pelabuhan, provinsi, kabkota, kecamatan, namasatker, ksop, nama_perusahaan, bidang_usaha, kategori, id_bidangusaha, id_kategori_bidangusaha, lokasi, alamat, penanggung_jawab, npwp, tersus_tuks, spesifikasi, tanggal_terbit, kbli, nib, masaberlaku, created_user, active) "
				        	   + "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	        portalTemplate.update(sqlTersus, new PreparedStatementSetter() {
						              public void setValues(PreparedStatement ps) throws SQLException {
						            	  ps.setString(1, dataTersus.getString("penyelenggara"));
						            	  ps.setString(2, dataTersus.getString("nama_provinsi"));
						            	  ps.setString(3, dataTersus.getString("aktifitas"));
						            	  ps.setString(4, dataTersus.getString("id"));
						            	  ps.setString(5, dataTersus.getString("code").isEmpty() ? null : dataTersus.getString("code"));
						            	  ps.setString(6, dataTersus.getString("sk"));
						            	  ps.setString(7, dataTersus.getString("lokasi_pelabuhan"));
						            	  ps.setString(8, dataTersus.getString("koordinat"));
						            	  ps.setString(9, dataTersus.getString("l_d"));
						            	  ps.setString(10, dataTersus.getString("l_m"));
						            	  ps.setString(11, dataTersus.getString("l_s"));
						            	  ps.setString(12, dataTersus.getString("lintang").isEmpty() ? null : dataTersus.getString("lintang"));
						            	  ps.setString(13, dataTersus.getString("b_d"));
						            	  ps.setString(14, dataTersus.getString("b_m"));
						            	  ps.setString(15, dataTersus.getString("b_s"));
						            	  ps.setString(16, dataTersus.getString("bujur").isEmpty() ? null : dataTersus.getString("bujur"));
						            	  ps.setString(17, dataTersus.getString("pelabuhan"));
						            	  ps.setString(18, dataTersus.getString("provinsi"));
						            	  ps.setString(19, dataTersus.getString("kabkota"));
						            	  ps.setString(20, dataTersus.getString("kecamatan"));
						            	  ps.setString(21, dataTersus.getString("namasatker"));
						            	  ps.setString(22, dataTersus.getString("ksop"));
						            	  ps.setString(23, dataTersus.getString("nama_perusahaan"));
						            	  ps.setString(24, dataTersus.getString("bidang_usaha"));
						            	  ps.setString(25, dataTersus.getString("kategori"));
						            	  ps.setString(26, dataTersus.getString("id_bidangusaha"));
						            	  ps.setString(27, dataTersus.getString("id_kategori_bidangusaha"));
						            	  ps.setString(28, dataTersus.getString("lokasi"));
						            	  ps.setString(29, dataTersus.getString("alamat"));
						            	  ps.setString(30, dataTersus.getString("penanggung_jawab"));
						            	  ps.setString(31, dataTersus.getString("npwp"));
						            	  ps.setString(32, dataTersus.getString("tersus_tuks"));
						            	  ps.setString(33, dataTersus.getString("spesifikasi"));
						            	  ps.setDate(34, Date.valueOf(dataTersus.getString("tanggal_terbit")));
						            	  ps.setString(35, dataTersus.getString("kbli").isEmpty() ? null : dataTersus.getString("kbli"));
						            	  ps.setString(36, dataTersus.getString("nib").isEmpty() ? null : dataTersus.getString("nib"));
						            	  ps.setDate(37, Date.valueOf(dataTersus.getString("masaberlaku")));
						            	  ps.setString(38, dataTersus.getString("created_user"));
						            	  ps.setString(39, dataTersus.getString("active"));
						              }
						         });

		}
	}

}
