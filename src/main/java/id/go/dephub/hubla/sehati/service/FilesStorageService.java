package id.go.dephub.hubla.sehati.service;
import java.util.Map;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

public interface FilesStorageService {
	public void init();
	public String save(String what, MultipartFile file);
	public String encodePDF(String path);
	public Map<String, String> verifySign(String path);
	public ResponseEntity<InputStreamResource> preview(String path);
	public Map<String, String> verifySignScan(String path);


}
