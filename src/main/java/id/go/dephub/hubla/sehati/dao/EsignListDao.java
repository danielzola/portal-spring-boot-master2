package id.go.dephub.hubla.sehati.dao;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;

import id.go.dephub.hubla.sehati.model.EsignDoc;
import id.go.dephub.hubla.sehati.model.EsignList;

public interface EsignListDao {
	void setEsignSync(HttpServletRequest request);
	List<EsignList> getEsignList(HttpServletRequest request);
	List<EsignList> getEsignData(String key);
	public String saveEsign(HttpServletRequest request);
	public String cekEsign(HttpServletRequest request);
	List<EsignDoc> getEsignDocList(HttpServletRequest request);
	List<EsignDoc> getEsignDocData(String key);
	public String saveEsignDoc(HttpServletRequest request);
	public String deleteEsignDoc(HttpServletRequest request);
	public ResponseEntity<InputStreamResource> preview(String id);
	public String signEsignDoc(HttpServletRequest request); 
}
