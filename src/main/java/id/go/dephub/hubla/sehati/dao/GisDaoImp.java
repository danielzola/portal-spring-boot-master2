package id.go.dephub.hubla.sehati.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import id.go.dephub.hubla.sehati.mapper.GisPortDetailMapper;
import id.go.dephub.hubla.sehati.mapper.GisPortMapper;
import id.go.dephub.hubla.sehati.mapper.GisSbnpDetailMapper;
import id.go.dephub.hubla.sehati.mapper.GisSbnpMapper;
import id.go.dephub.hubla.sehati.mapper.GisSearchMapper;
import id.go.dephub.hubla.sehati.mapper.GisShipDetailMapper;
import id.go.dephub.hubla.sehati.mapper.GisShipMapper;
import id.go.dephub.hubla.sehati.mapper.GisSropDetailMapper;
import id.go.dephub.hubla.sehati.mapper.GisSropMapper;
import id.go.dephub.hubla.sehati.mapper.GisTersusDetailMapper;
import id.go.dephub.hubla.sehati.mapper.GisTersusMapper;
import id.go.dephub.hubla.sehati.mapper.GisVtsDetailMapper;
import id.go.dephub.hubla.sehati.mapper.GisVtsMapper;
import id.go.dephub.hubla.sehati.model.GisPort;
import id.go.dephub.hubla.sehati.model.GisPortDetail;
import id.go.dephub.hubla.sehati.model.GisSbnp;
import id.go.dephub.hubla.sehati.model.GisSbnpDetail;
import id.go.dephub.hubla.sehati.model.GisSearch;
import id.go.dephub.hubla.sehati.model.GisShip;
import id.go.dephub.hubla.sehati.model.GisShipDetail;
import id.go.dephub.hubla.sehati.model.GisSrop;
import id.go.dephub.hubla.sehati.model.GisSropDetail;
import id.go.dephub.hubla.sehati.model.GisTersus;
import id.go.dephub.hubla.sehati.model.GisTersusDetail;
import id.go.dephub.hubla.sehati.model.GisVts;
import id.go.dephub.hubla.sehati.model.GisVtsDetail;

@Repository
public class GisDaoImp implements GisDao {
	@Autowired
	@Qualifier("datalakeJdbcTemplate")
	private JdbcTemplate datalakeTemplate;

	@Autowired
	@Qualifier("datalakeOldJdbcTemplate")
	private JdbcTemplate datalakeOldTemplate;
	
    @Override
    public List<GisPort> getPortAll(String what, String key) {
    	if(what.equals("")) {
            String sql = "select a.nama_pelabuhan, a.kode_pelabuhan, b.nama kabkota, ((trim(split_part(replace(replace(replace(a.koordinat_bujur ,'°','|'),'''','|'),'\"','|'),'|',1))::float)+((trim(split_part(replace(replace(replace(a.koordinat_bujur ,'°','|'),'''','|'),'\"','|'),'|',2))::float)/60)+((trim(split_part(replace(replace(replace(a.koordinat_bujur ,'°','|'),'''','|'),'\"','|'),'|',3))::float)/(60*60)))*(case when trim(split_part(replace(replace(replace(a.koordinat_bujur ,'°','|'),'''','|'),'\"','|'),'|',4))='S' or trim(split_part(replace(replace(replace(a.koordinat_bujur ,'°','|'),'''','|'),'\"','|'),'|',4))='W' then -1 else 1 end) longitude,((trim(split_part(replace(replace(replace(a.koordinat_lintang ,'°','|'),'''','|'),'\"','|'),'|',1))::float)+((trim(split_part(replace(replace(replace(a.koordinat_lintang ,'°','|'),'''','|'),'\"','|'),'|',2))::float)/60)+((trim(split_part(replace(replace(replace(a.koordinat_bujur ,'°','|'),'''','|'),'\"','|'),'|',3))::float)/(60*60)))*(case when trim(split_part(replace(replace(replace(a.koordinat_lintang ,'°','|'),'''','|'),'\"','|'),'|',4))='S' or trim(split_part(replace(replace(replace(a.koordinat_lintang ,'°','|'),'''','|'),'\"','|'),'|',4))='W' then -1 else 1 end) latitude from gold.m_pelabuhan a left join gold.m_regional b on a.id_kabkota = b.id_kemendagri where a.koordinat_bujur is not null and a.koordinat_lintang is not null";
            List<GisPort> rest = datalakeTemplate.query(sql, new GisPortMapper());
            return rest;    		
    	}else {
    		if(what.equals("satker")) {
                String sql = "select a.nama_pelabuhan, a.kode_pelabuhan, b.nama kabkota, ((trim(split_part(replace(replace(replace(a.koordinat_bujur ,'°','|'),'''','|'),'\"','|'),'|',1))::float)+((trim(split_part(replace(replace(replace(a.koordinat_bujur ,'°','|'),'''','|'),'\"','|'),'|',2))::float)/60)+((trim(split_part(replace(replace(replace(a.koordinat_bujur ,'°','|'),'''','|'),'\"','|'),'|',3))::float)/(60*60)))*(case when trim(split_part(replace(replace(replace(a.koordinat_bujur ,'°','|'),'''','|'),'\"','|'),'|',4))='S' or trim(split_part(replace(replace(replace(a.koordinat_bujur ,'°','|'),'''','|'),'\"','|'),'|',4))='W' then -1 else 1 end) longitude,((trim(split_part(replace(replace(replace(a.koordinat_lintang ,'°','|'),'''','|'),'\"','|'),'|',1))::float)+((trim(split_part(replace(replace(replace(a.koordinat_lintang ,'°','|'),'''','|'),'\"','|'),'|',2))::float)/60)+((trim(split_part(replace(replace(replace(a.koordinat_bujur ,'°','|'),'''','|'),'\"','|'),'|',3))::float)/(60*60)))*(case when trim(split_part(replace(replace(replace(a.koordinat_lintang ,'°','|'),'''','|'),'\"','|'),'|',4))='S' or trim(split_part(replace(replace(replace(a.koordinat_lintang ,'°','|'),'''','|'),'\"','|'),'|',4))='W' then -1 else 1 end) latitude from gold.m_pelabuhan a left join gold.m_regional b on a.id_kabkota = b.id_kemendagri where a.koordinat_bujur is not null and a.koordinat_lintang is not null and a.kode_upt=?";
                @SuppressWarnings("deprecation")
				List<GisPort> rest = datalakeTemplate.query(sql, new Object[] {key}, new GisPortMapper());
                return rest;    		
    			
    		}else {
        		if(what.equals("propinsi")) {
                    String sql = "select a.nama_pelabuhan, a.kode_pelabuhan, b.nama kabkota, ((trim(split_part(replace(replace(replace(a.koordinat_bujur ,'°','|'),'''','|'),'\"','|'),'|',1))::float)+((trim(split_part(replace(replace(replace(a.koordinat_bujur ,'°','|'),'''','|'),'\"','|'),'|',2))::float)/60)+((trim(split_part(replace(replace(replace(a.koordinat_bujur ,'°','|'),'''','|'),'\"','|'),'|',3))::float)/(60*60)))*(case when trim(split_part(replace(replace(replace(a.koordinat_bujur ,'°','|'),'''','|'),'\"','|'),'|',4))='S' or trim(split_part(replace(replace(replace(a.koordinat_bujur ,'°','|'),'''','|'),'\"','|'),'|',4))='W' then -1 else 1 end) longitude,((trim(split_part(replace(replace(replace(a.koordinat_lintang ,'°','|'),'''','|'),'\"','|'),'|',1))::float)+((trim(split_part(replace(replace(replace(a.koordinat_lintang ,'°','|'),'''','|'),'\"','|'),'|',2))::float)/60)+((trim(split_part(replace(replace(replace(a.koordinat_bujur ,'°','|'),'''','|'),'\"','|'),'|',3))::float)/(60*60)))*(case when trim(split_part(replace(replace(replace(a.koordinat_lintang ,'°','|'),'''','|'),'\"','|'),'|',4))='S' or trim(split_part(replace(replace(replace(a.koordinat_lintang ,'°','|'),'''','|'),'\"','|'),'|',4))='W' then -1 else 1 end) latitude from gold.m_pelabuhan a left join gold.m_regional b on a.id_kabkota = b.id_kemendagri left join gold.m_regional c on b.fk_parent_id=c.id_kemendagri where a.koordinat_bujur is not null and a.koordinat_lintang is not null and c.nama=?";
                    @SuppressWarnings("deprecation")
    				List<GisPort> rest = datalakeTemplate.query(sql, new Object[] {key}, new GisPortMapper());
                    return rest;    		
        			
        		}else {
        			return null;
        		}
    		}
    	}
    }
    
    @Override
    public List<GisSbnp> getSbnpAll() {
        String sql = "select a.id_suar, replace(a.nm_location, '\"', '') nm_location, a.dsi_nr, a.longitude, a.latitude from gold.gis_sbnp a where a.latitude is not null and a.longitude is not null";
        List<GisSbnp> rest = datalakeTemplate.query(sql, new GisSbnpMapper());
        return rest;
    }

	@Override
	public List<GisSrop> getSropAll() {
        String sql = "select id id_srop, nama_srop, call_sign_srop, koordinat_lintang latitude, koordinat_bujur longitude from gold.gis_srop_pt where koordinat_lintang is not null and koordinat_bujur is not null";
        List<GisSrop> rest = datalakeTemplate.query(sql, new GisSropMapper());
		return rest;
	}

	@Override
	public List<GisVts> getVtsAll() {
        String sql = "select id id_vts, namobj nama_vts, jns_lynn, lat latitude, long longitude from gold.gis_vts_pt where lat is not null and long is not null";
        List<GisVts> rest = datalakeTemplate.query(sql, new GisVtsMapper());
		return rest;
	}

	@Override
	public List<GisTersus> getTersusAll(String what, String key) {
		
    	if(what.equals("")) {
            String sql = "select a.id id_tersus, b.nama_perusahaan nama_tersus, b.tersus_tuks, a.koordinat_lintang longitude, a.koordinat_bujur latitude "
 	        	   + "from gold.gis_tersus_tuks a "
 	        	   + "left join gold.m_tersus_tuks b on replace(replace(replace(replace(trim(a.izin_pembangunan),' ',''),'.',''),'-',''),'/','') = replace(replace(replace(replace(trim(b.sk),' ',''),'.',''),'-',''),'/','') "
 	        	   + "where b.id is not null and a.koordinat_bujur is not null and a.koordinat_bujur is not null";
         List<GisTersus> rest = datalakeTemplate.query(sql, new GisTersusMapper());
 		return rest;
    	}else {
    		if(what.equals("satker")) {
    	        String sql = "select a.id id_tersus, b.nama_perusahaan nama_tersus, b.tersus_tuks, a.koordinat_lintang longitude, a.koordinat_bujur latitude "
    		        	   + "from gold.gis_tersus_tuks a "
    		        	   + "left join gold.m_tersus_tuks b on replace(replace(replace(replace(trim(a.izin_pembangunan),' ',''),'.',''),'-',''),'/','') = replace(replace(replace(replace(trim(b.sk),' ',''),'.',''),'-',''),'/','') "
    		        	   + "where b.id is not null and b.ksop = ? and a.koordinat_bujur is not null and a.koordinat_bujur is not null";
    	        @SuppressWarnings("deprecation")
				List<GisTersus> rest = datalakeTemplate.query(sql, new Object[] {key}, new GisTersusMapper());
    			return rest;
    		}else {
        		if(what.equals("propinsi")) {
        	        String sql = "select a.id id_tersus, b.nama_perusahaan nama_tersus, b.tersus_tuks, a.koordinat_lintang longitude, a.koordinat_bujur latitude "
        		        	   + "from gold.gis_tersus_tuks a "
        		        	   + "left join gold.m_tersus_tuks b on replace(replace(replace(replace(trim(a.izin_pembangunan),' ',''),'.',''),'-',''),'/','') = replace(replace(replace(replace(trim(b.sk),' ',''),'.',''),'-',''),'/','') "
        		        	   + "where b.id is not null and a.koordinat_bujur is not null and a.koordinat_bujur is not null";
        	        List<GisTersus> rest = datalakeTemplate.query(sql, new GisTersusMapper());
        			return rest;
        		}else {
        			return null;
        		}
    		}
    	}
		
	}

	@Override
	public List<GisShip> getShipAll(String what, String key) {
    	if(what.equals("")) {		
	        String sql = "SELECT DISTINCT on(trim(a.mmsi)||replace(trim(a.vessel_name),' ','')) trim(a.mmsi)||replace(trim(a.vessel_name),' ','') kapal_id, "
		        		+ "a.mmsi, "
		        		+ "replace(replace(trim(a.vessel_name),',',''),'\"','') vessel_name, "
		        		+ "a.latitude, a.longitude, c.\"JENISDETAIL_KET\" jenis_kapal, d.\"JENIS_KET\" tipe_kapal, "
		        		+ "(case when trim(a.true_heading)='' then '0' else coalesce(a.true_heading,'0') end) true_heading "
		        		+ "from ais.tb_ais a "
		        		+ "LEFT JOIN data_normal_new.n_kapal_umum b ON lower(replace(replace(replace(replace(replace(a.vessel_name, ' ', ''), '-', ''), '_', ''), ',', ''), '.', '')) = lower(replace(replace(replace(replace(replace(b.nama_kapal, ' ', ''), '-', ''), '_', ''), ',', ''), '.', '')) "
		        		+ "LEFT JOIN data_normal_new.m_daftar_jenisdetail_kapal c ON b.jenis_kapal=c.\"JENISDETAIL_ID\" "
		        		+ "LEFT JOIN data_normal_new.m_daftar_jenis_kapal d ON c.\"JENIS_ID\" =d.\"JENIS_ID\" "
		        		+ "WHERE "
		        		+ "b.nama_kapal is not null AND "
		        		+ "length(a.vessel_name)>0 AND "
		        		+ "length(trim(a.latitude))>0 AND " 
		        		+ "length(trim(a.longitude))>0 AND "
		        		+ "a.latitude is not null and a.longitude is not null and a.received_time_utc_unix>='2020-04-15 00:00:00' "
		        		+ "ORDER BY (trim(a.mmsi)||replace(trim(a.vessel_name),' ','')), a.received_time_utc_unix DESC";
	        System.out.print(sql);
	        List<GisShip> rest = datalakeOldTemplate.query(sql, new GisShipMapper());
			return rest;
    	}else {
        	if(what.equals("bendera")) {
    	        String sql = "SELECT DISTINCT on(trim(a.mmsi)||replace(trim(a.vessel_name),' ','')) trim(a.mmsi)||replace(trim(a.vessel_name),' ','') kapal_id, "
		        		+ "a.mmsi, "
		        		+ "replace(replace(trim(a.vessel_name),',',''),'\"','') vessel_name, "
		        		+ "a.latitude, a.longitude, c.\"JENISDETAIL_KET\" jenis_kapal, d.\"JENIS_KET\" tipe_kapal, "
		        		+ "(case when trim(a.true_heading)='' then '0' else coalesce(a.true_heading,'0') end) true_heading "
		        		+ "from ais.tb_ais a "
		        		+ "LEFT JOIN data_normal_new.n_kapal_umum b ON lower(replace(replace(replace(replace(replace(a.vessel_name, ' ', ''), '-', ''), '_', ''), ',', ''), '.', '')) = lower(replace(replace(replace(replace(replace(b.nama_kapal, ' ', ''), '-', ''), '_', ''), ',', ''), '.', '')) "
		        		+ "LEFT JOIN data_normal_new.m_daftar_jenisdetail_kapal c ON b.jenis_kapal=c.\"JENISDETAIL_ID\" "
		        		+ "LEFT JOIN data_normal_new.m_daftar_jenis_kapal d ON c.\"JENIS_ID\" =d.\"JENIS_ID\" "
		        		+ "WHERE "
		        		+ "b.nama_kapal is not null AND b.bendera_asal = ? AND "
		        		+ "length(a.vessel_name)>0 AND "
		        		+ "length(trim(a.latitude))>0 AND " 
		        		+ "length(trim(a.longitude))>0 AND "
		        		+ "a.latitude is not null and a.longitude is not null and a.received_time_utc_unix>='2020-04-15 00:00:00' "
		        		+ "ORDER BY (trim(a.mmsi)||replace(trim(a.vessel_name),' ','')), a.received_time_utc_unix DESC";
    	        System.out.print(sql);
		        @SuppressWarnings("deprecation")
				List<GisShip> rest = datalakeOldTemplate.query(sql, new Object[] {Integer.valueOf(key)}, new GisShipMapper());
				return rest;        		
        	}else {
            	if(what.equals("pemilik")) {
            		if(key.equals("1")) {
            	        String sql = "SELECT DISTINCT on(trim(a.mmsi)||replace(trim(a.vessel_name),' ','')) trim(a.mmsi)||replace(trim(a.vessel_name),' ','') kapal_id, "
        		        		+ "a.mmsi, "
        		        		+ "replace(replace(trim(a.vessel_name),',',''),'\"','') vessel_name, "
        		        		+ "a.latitude, a.longitude, c.\"JENISDETAIL_KET\" jenis_kapal, d.\"JENIS_KET\" tipe_kapal, "
        		        		+ "(case when trim(a.true_heading)='' then '0' else coalesce(a.true_heading,'0') end) true_heading "
        		        		+ "from ais.tb_ais a "
        		        		+ "LEFT JOIN data_normal_new.n_kapal_umum b ON lower(replace(replace(replace(replace(replace(a.vessel_name, ' ', ''), '-', ''), '_', ''), ',', ''), '.', '')) = lower(replace(replace(replace(replace(replace(b.nama_kapal, ' ', ''), '-', ''), '_', ''), ',', ''), '.', '')) "
        		        		+ "LEFT JOIN data_normal_new.m_daftar_jenisdetail_kapal c ON b.jenis_kapal=c.\"JENISDETAIL_ID\" "
        		        		+ "LEFT JOIN data_normal_new.m_daftar_jenis_kapal d ON c.\"JENIS_ID\" =d.\"JENIS_ID\" "
        		        		+ "WHERE "
        		        		+ "b.nama_kapal is not null AND b.jenis_pemilik in(1,2) AND "
        		        		+ "length(a.vessel_name)>0 AND "
        		        		+ "length(trim(a.latitude))>0 AND " 
        		        		+ "length(trim(a.longitude))>0 AND "
        		        		+ "a.latitude is not null and a.longitude is not null and a.received_time_utc_unix>='2020-04-15 00:00:00' "
        		        		+ "ORDER BY (trim(a.mmsi)||replace(trim(a.vessel_name),' ','')), a.received_time_utc_unix DESC";
            	        System.out.print(sql);
        		        List<GisShip> rest = datalakeOldTemplate.query(sql, new GisShipMapper());
        				return rest;        		            			
            		}else {
            	        String sql = "SELECT DISTINCT on(trim(a.mmsi)||replace(trim(a.vessel_name),' ','')) trim(a.mmsi)||replace(trim(a.vessel_name),' ','') kapal_id, "
        		        		+ "a.mmsi, "
        		        		+ "replace(replace(trim(a.vessel_name),',',''),'\"','') vessel_name, "
        		        		+ "a.latitude, a.longitude, c.\"JENISDETAIL_KET\" jenis_kapal, d.\"JENIS_KET\" tipe_kapal, "
        		        		+ "(case when trim(a.true_heading)='' then '0' else coalesce(a.true_heading,'0') end) true_heading "
        		        		+ "from ais.tb_ais a "
        		        		+ "LEFT JOIN data_normal_new.n_kapal_umum b ON lower(replace(replace(replace(replace(replace(a.vessel_name, ' ', ''), '-', ''), '_', ''), ',', ''), '.', '')) = lower(replace(replace(replace(replace(replace(b.nama_kapal, ' ', ''), '-', ''), '_', ''), ',', ''), '.', '')) "
        		        		+ "LEFT JOIN data_normal_new.m_daftar_jenisdetail_kapal c ON b.jenis_kapal=c.\"JENISDETAIL_ID\" "
        		        		+ "LEFT JOIN data_normal_new.m_daftar_jenis_kapal d ON c.\"JENIS_ID\" =d.\"JENIS_ID\" "
        		        		+ "WHERE "
        		        		+ "b.nama_kapal is not null AND b.jenis_pemilik = 3 AND "
        		        		+ "length(a.vessel_name)>0 AND "
        		        		+ "length(trim(a.latitude))>0 AND " 
        		        		+ "length(trim(a.longitude))>0 AND "
        		        		+ "a.latitude is not null and a.longitude is not null and a.received_time_utc_unix>='2020-04-15 00:00:00' "
        		        		+ "ORDER BY (trim(a.mmsi)||replace(trim(a.vessel_name),' ','')), a.received_time_utc_unix DESC";
            	        System.out.print(sql);
        		        List<GisShip> rest = datalakeOldTemplate.query(sql, new GisShipMapper());
        				return rest;        		            			            			
            		}
            	}else {
            		return null;
            	}  
        	}    		
    	}
	}

	@Override
	public List<GisSearch> getSearch(String key) {
        String sql = "select a.kode_pelabuhan id, 'PELABUHAN' tipe, a.nama_pelabuhan nama, ((trim(split_part(replace(replace(replace(a.koordinat_bujur ,'°','|'),'''','|'),'\"','|'),'|',1))::float)+((trim(split_part(replace(replace(replace(a.koordinat_bujur ,'°','|'),'''','|'),'\"','|'),'|',2))::float)/60)+((trim(split_part(replace(replace(replace(a.koordinat_bujur ,'°','|'),'''','|'),'\"','|'),'|',3))::float)/(60*60)))*(case when trim(split_part(replace(replace(replace(a.koordinat_bujur ,'°','|'),'''','|'),'\"','|'),'|',4))='S' or trim(split_part(replace(replace(replace(a.koordinat_bujur ,'°','|'),'''','|'),'\"','|'),'|',4))='W' then -1 else 1 end) longitude,((trim(split_part(replace(replace(replace(a.koordinat_lintang ,'°','|'),'''','|'),'\"','|'),'|',1))::float)+((trim(split_part(replace(replace(replace(a.koordinat_lintang ,'°','|'),'''','|'),'\"','|'),'|',2))::float)/60)+((trim(split_part(replace(replace(replace(a.koordinat_bujur ,'°','|'),'''','|'),'\"','|'),'|',3))::float)/(60*60)))*(case when trim(split_part(replace(replace(replace(a.koordinat_lintang ,'°','|'),'''','|'),'\"','|'),'|',4))='S' or trim(split_part(replace(replace(replace(a.koordinat_lintang ,'°','|'),'''','|'),'\"','|'),'|',4))='W' then -1 else 1 end) latitude from gold.m_pelabuhan a left join gold.m_regional b on a.id_kabkota = b.id_kemendagri where a.koordinat_bujur is not null and a.koordinat_lintang is not null and UPPER(a.nama_pelabuhan) like ?";
        @SuppressWarnings("deprecation")
		List<GisSearch> rest = datalakeTemplate.query(sql, new Object[] {"%" + key.replace("+", " ").toUpperCase() + "%"}, new GisSearchMapper());
        
        String sql2 = "select a.id_suar id, 'SBNP' tipe, replace(a.nm_location, '\"', '') nama, a.longitude, a.latitude from gold.gis_sbnp a where a.latitude is not null and a.longitude is not null and UPPER(a.nm_location) like ?";
        @SuppressWarnings("deprecation")
		List<GisSearch> rest2 = datalakeTemplate.query(sql2, new Object[] {"%" + key.replace("+", " ").toUpperCase() + "%"}, new GisSearchMapper());
        
        String sql3 = "select id, 'SROP' tipe, nama_srop nama, koordinat_bujur longitude, koordinat_lintang latitude from gold.gis_srop_pt where koordinat_lintang is not null and koordinat_bujur is not null and UPPER(nama_srop) like ?";
        @SuppressWarnings("deprecation")
		List<GisSearch> rest3 = datalakeTemplate.query(sql3, new Object[] {"%" + key.replace("+", " ").toUpperCase() + "%"}, new GisSearchMapper());
        
        String sql4 = "select id, 'VTS' tipe, namobj nama, long longitude, lat latitude from gold.gis_vts_pt where lat is not null and long is not null and UPPER(namobj) like ?";
        @SuppressWarnings("deprecation")
		List<GisSearch> rest4 = datalakeTemplate.query(sql4, new Object[] {"%" + key.replace("+", " ").toUpperCase() + "%"}, new GisSearchMapper());
        
        String sql5 = "select b.id, 'TERSUS/TUKS' tipe, b.nama_perusahaan nama, a.koordinat_lintang longitude, a.koordinat_bujur latitude "
	        	    + "from gold.gis_tersus_tuks a "
	        	    + "left join gold.m_tersus_tuks b on replace(replace(replace(replace(trim(a.izin_pembangunan),' ',''),'.',''),'-',''),'/','') = replace(replace(replace(replace(trim(b.sk),' ',''),'.',''),'-',''),'/','') "
	        	    + "where b.id is not null and a.koordinat_bujur is not null and a.koordinat_bujur is not null and UPPER(b.nama_perusahaan) like ?";
        @SuppressWarnings("deprecation")
		List<GisSearch> rest5 = datalakeTemplate.query(sql5, new Object[] {"%" + key.replace("+", " ").toUpperCase() + "%"}, new GisSearchMapper());
        
        String sql6 = "SELECT DISTINCT on(trim(a.mmsi)||replace(trim(a.vessel_name),' ','')) trim(a.mmsi)||replace(trim(a.vessel_name),' ','') id, 'KAPAL' tipe, "
	        		+ "replace(replace(trim(a.vessel_name),',',''),'\"','') nama, "
	        		+ "a.latitude, a.longitude "
	        		+ "from ais.tb_ais a "
	        		+ "LEFT JOIN data_normal_new.n_kapal_umum b ON lower(replace(replace(replace(replace(replace(a.vessel_name, ' ', ''), '-', ''), '_', ''), ',', ''), '.', '')) = lower(replace(replace(replace(replace(replace(b.nama_kapal, ' ', ''), '-', ''), '_', ''), ',', ''), '.', '')) "
	        		+ "LEFT JOIN data_normal_new.m_daftar_jenisdetail_kapal c ON b.jenis_kapal=c.\"JENISDETAIL_ID\" "
	        		+ "LEFT JOIN data_normal_new.m_daftar_jenis_kapal d ON c.\"JENIS_ID\" =d.\"JENIS_ID\" "
	        		+ "WHERE "
	        		+ "b.nama_kapal is not null AND "
	        		+ "length(a.vessel_name)>0 AND "
	        		+ "length(trim(a.latitude))>0 AND " 
	        		+ "length(trim(a.longitude))>0 AND "
	        		+ "a.latitude is not null and a.longitude is not null and a.received_time_utc_unix>='2020-04-01 00:00:00' and UPPER(a.vessel_name) like ? "        
	             	+ "ORDER BY (trim(a.mmsi)||replace(trim(a.vessel_name),' ','')), a.received_time_utc_unix DESC";
        @SuppressWarnings("deprecation")
		List<GisSearch> rest6 = datalakeOldTemplate.query(sql6, new Object[] {"%" + key.replace("+", " ").toUpperCase() + "%"}, new GisSearchMapper());
    
        List<GisSearch> joinedList = new ArrayList<>();
        Stream.of(rest, rest2, rest3, rest4, rest5, rest6).forEach(joinedList::addAll);
        return joinedList;
	}

	@Override
	public List<GisPortDetail> getPortDetail(String key) {
        String sql = "select a.kode_pelabuhan, a.nama_pelabuhan nama_pelabuhan, a.koordinat_lintang latitude, a.koordinat_bujur longitude, b.nama kabkota, c.nama provinsi, e.nama_jenis_singkat||' '||f.nama_kelas||' '||d.upt satker "
        		   + "from gold.m_pelabuhan a "
        		   + "left join gold.m_regional b on a.id_kabkota = b.id_kemendagri "
        		   + "left join gold.m_regional c on b.fk_parent_id = c.id_kemendagri "
        		   + "left join gold.m_satker d on a.kode_upt = d.kode_satker_kemenkeu "
        		   + "left join gold.m_jenis_upt e on d.jenis_upt = e.id "
        		   + "left join gold.m_kelas_satker f on d.kelas = f.id "
        		   + "where a.koordinat_bujur is not null and a.koordinat_lintang is not null and a.kode_pelabuhan = ?";
        @SuppressWarnings("deprecation")
		List<GisPortDetail> rest = datalakeTemplate.query(sql, new Object[] {key}, new GisPortDetailMapper());
		return rest;
	}

	@Override
	public List<GisShipDetail> getShipDetail(String key) {
        String sql  = "SELECT DISTINCT on(trim(a.mmsi)||replace(trim(a.vessel_name),' ','')) trim(a.mmsi)||replace(trim(a.vessel_name),' ','') kapal_id, trim(a.mmsi) mmsi, a.mid, replace(replace(trim(a.vessel_name),',',''),'\"','') vessel_name, b.call_sign, "
        			+ "c1.\"JENIS_KET\"||', '||c.\"JENISDETAIL_KET\" jenis_kapal, (case when length(trim(a.imo_number))>0 then trim(a.imo_number) when length(trim(b.nomor_imo))>0 then trim(b.nomor_imo) else '-' end) imo_number, a.course_over_ground, a.speed_over_ground, "
        			+ "a.destination, TO_CHAR(a.received_time_utc_unix + INTERVAL '7 HOUR','DD-MM-YYYY HH24:MI:SS') received_time_utc_unix, a.latitude, a.longitude, b.nama_pemilik, b.alamat_pemilik, b.tempat_pembuatan, b.tahun_pembuatan, b.no_akta, b.tempat_pendaftaran, "
        			+ "TO_CHAR(b.tgl_pendaftaran, 'DD-MM-YYYY') tgl_pendaftaran, b.no_tanda_pendaftaran, b.no_surat_ukur, TO_CHAR(b.tgl_surat_ukur, 'DD-MM-YYYY') tgl_surat_ukur, b.dimensi_dalam, b.dimensi_isibersih, b.dimensi_loa, "
        			+ "d.\"BAHAN_KET\" bahan_ket, e.\"PENGGERAK_KET\" penggerak_ket, b.jml_cerobong, b.jml_geladak, b.jml_baling, b.mesin_merk, b.daya_mesin1, b.satuan_mesin1, b.dimensi_lebar, b.dimensi_panjang, b.dimensi_isikotor "
	        		+ "FROM ais.tb_ais a "
	        		+ "LEFT JOIN data_normal_new.n_kapal_umum b ON lower(replace(replace(replace(replace(replace(a.vessel_name, ' ', ''), '-', ''), '_', ''), ',', ''), '.', '')) = lower(replace(replace(replace(replace(replace(b.nama_kapal, ' ', ''), '-', ''), '_', ''), ',', ''), '.', '')) "
	        		+ "LEFT JOIN data_normal_new.m_daftar_jenisdetail_kapal c ON b.jenis_kapal=c.\"JENISDETAIL_ID\" "
	        		+ "LEFT JOIN data_normal_new.m_daftar_jenis_kapal c1 ON c.\"JENIS_ID\" =c1.\"JENIS_ID\" "
	        		+ "LEFT JOIN data_normal_new.m_daftar_bahan_kapal d ON b.bahan_kapal=d.\"BAHAN_ID\" "
	        		+ "LEFT JOIN data_normal_new.m_daftar_penggerak_kapal e ON b.penggerak_kapal=e.\"PENGGERAK_ID\" "
	        		+ "WHERE "
	        		+ "b.nama_kapal is not null AND "
	        		+ "length(a.vessel_name)>0 AND "
	        		+ "length(trim(a.latitude))>0 AND "
	        		+ "length(trim(a.longitude))>0 AND "
	        		+ "a.latitude is not null and a.longitude is not null and a.received_time_utc_unix>='2020-04-15 00:00:00' AND "
	        		+ "(trim(a.mmsi)||replace(trim(a.vessel_name),' ',''))=?"
	        		+ "ORDER BY (trim(a.mmsi)||replace(trim(a.vessel_name),' ','')), a.received_time_utc_unix DESC";
        @SuppressWarnings("deprecation")
		List<GisShipDetail> rest = datalakeOldTemplate.query(sql, new Object[] {key}, new GisShipDetailMapper());
		return rest;
	}
	
	@Override
	public List<GisSbnpDetail> getSbnpDetail(int key) {
        String sql = "select a.id_suar, replace(a.nm_location, '\"', '') nm_location, a.dsi_nr, a.longitude, a.latitude, a.description, "
        		   + "a.power_char||' '||coalesce(a.power_type,' ') power, coalesce(b.desc,'-') jenis, coalesce(a.high,'-') high, coalesce(a.range,'-') jangkauan, "
        		   + "(case when a.owner='Y' then 'DJPL' when a.owner='N' then a.non_djpl else '-' end) pemilik, a.remark, a.elevation "
        		   + "from gold.gis_sbnp a "
		           + "left join gold.gis_sbnp_tipe_suar b ON a.type_suar=b.code "
        		   + "where a.latitude is not null and a.longitude is not null and a.id_suar=?";
        @SuppressWarnings("deprecation")
		List<GisSbnpDetail> rest = datalakeTemplate.query(sql, new Object[] {key}, new GisSbnpDetailMapper());
		return rest;
	}

	@Override
	public List<GisSropDetail> getSropDetail(int key) {
        String sql = "select a.id, a.nama_srop, a.call_sign_srop, a.koordinat_lintang, a.koordinat_bujur, a.kelas_srop, a.coverage_area "
        		   + "from gold.gis_srop_pt a "
        		   + "where a.koordinat_lintang is not null and a.koordinat_bujur is not null and id=?";
        @SuppressWarnings("deprecation")
		List<GisSropDetail> rest = datalakeTemplate.query(sql, new Object[] {key}, new GisSropDetailMapper());
		return rest;
	}

	@Override
	public List<GisVtsDetail> getVtsDetail(int key) {
        String sql = "select id, namobj, jns_lynn, lat latitude, long longitude, alat, frek_krj from gold.gis_vts_pt where lat is not null and long is not null and id=?";
	    @SuppressWarnings("deprecation")
	    List<GisVtsDetail> rest = datalakeTemplate.query(sql, new Object[] {key}, new GisVtsDetailMapper());
		return rest;
	}

	@Override
	public List<GisTersusDetail> getTersusDetail(int key) {
        String sql = "select a.id, b.nama_perusahaan nama_tersus, b.bidang_usaha, b.tersus_tuks, b.koordinat "
	        	   + "from gold.gis_tersus_tuks a "
	        	   + "left join gold.m_tersus_tuks b on replace(replace(replace(replace(trim(a.izin_pembangunan),' ',''),'.',''),'-',''),'/','') = replace(replace(replace(replace(trim(b.sk),' ',''),'.',''),'-',''),'/','') "
	        	   + "where b.id is not null and a.koordinat_bujur is not null and a.koordinat_bujur is not null and a.id=?";
	    @SuppressWarnings("deprecation")
	    List<GisTersusDetail> rest = datalakeTemplate.query(sql, new Object[] {key}, new GisTersusDetailMapper());
		return rest;
	}
	

}
