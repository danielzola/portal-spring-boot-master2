package id.go.dephub.hubla.sehati.controller;

import id.go.dephub.hubla.sehati.dao.HomeDao;
import id.go.dephub.hubla.sehati.dao.UserDao;
import id.go.dephub.hubla.sehati.dto.GeneralResponse;
import id.go.dephub.hubla.sehati.dto.TokenResponse;
import id.go.dephub.hubla.sehati.service.FilesStorageService;
import id.go.dephub.hubla.sehati.service.RegisterService;
import id.go.dephub.hubla.sehati.service.TokenService;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
@RequestMapping("token")
public class TokenController extends BaseController {


    @Autowired
    TokenService tokenService;


    @PostMapping("/generate")
    public ResponseEntity<?> getVerifySign() {
        System.out.println("masuk controller");
        return ok(tokenService.getTokenResponse());
    }
}
