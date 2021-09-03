package id.go.dephub.hubla.sehati.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import id.go.dephub.hubla.sehati.dao.GisDao;
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

@Controller
@RequestMapping("gis")
public class GisController {
	 @Autowired
	 GisDao restService;
	 
	 @GetMapping("/port")
     public ResponseEntity<List<GisPort>> getPortAll() {
        List<GisPort> listRest = restService.getPortAll("","");
        return new ResponseEntity<List<GisPort>>(listRest, HttpStatus.OK);
     }
	 
	 @GetMapping("/port/{what}/{key}")
     public ResponseEntity<List<GisPort>> getPortKey(@PathVariable("what") String what, @PathVariable("key") String key) {
        List<GisPort> listRest = restService.getPortAll(what, key);
        return new ResponseEntity<List<GisPort>>(listRest, HttpStatus.OK);
     }
	 
	 @GetMapping("/port/detail/{key}")
     public ResponseEntity<List<GisPortDetail>> getPortDetail(@PathVariable("key") String key) {
        List<GisPortDetail> listRest = restService.getPortDetail(key);
        return new ResponseEntity<List<GisPortDetail>>(listRest, HttpStatus.OK);
     }
	 
	 @GetMapping("/sbnp")
     public ResponseEntity<List<GisSbnp>> getSbnpAll() {
        List<GisSbnp> listRest = restService.getSbnpAll();
        return new ResponseEntity<List<GisSbnp>>(listRest, HttpStatus.OK);
     }
	 
	 @GetMapping("/sbnp/detail/{key}")
     public ResponseEntity<List<GisSbnpDetail>> getSbnpDetail(@PathVariable("key") int key) {
        List<GisSbnpDetail> listRest = restService.getSbnpDetail(key);
        return new ResponseEntity<List<GisSbnpDetail>>(listRest, HttpStatus.OK);
     }
	 
	 @GetMapping("/srop")
     public ResponseEntity<List<GisSrop>> getSropAll() {
        List<GisSrop> listRest = restService.getSropAll();
        return new ResponseEntity<List<GisSrop>>(listRest, HttpStatus.OK);
     }

	 @GetMapping("/srop/detail/{key}")
     public ResponseEntity<List<GisSropDetail>> getSropDetail(@PathVariable("key") int key) {
        List<GisSropDetail> listRest = restService.getSropDetail(key);
        return new ResponseEntity<List<GisSropDetail>>(listRest, HttpStatus.OK);
     }
	 
	 @GetMapping("/vts")
     public ResponseEntity<List<GisVts>> getVtsAll() {
        List<GisVts> listRest = restService.getVtsAll();
        return new ResponseEntity<List<GisVts>>(listRest, HttpStatus.OK);
     }

	 @GetMapping("/vts/detail/{key}")
     public ResponseEntity<List<GisVtsDetail>> getVtsDetail(@PathVariable("key") int key) {
        List<GisVtsDetail> listRest = restService.getVtsDetail(key);
        return new ResponseEntity<List<GisVtsDetail>>(listRest, HttpStatus.OK);
     }
	 
	 @GetMapping("/tersus")
     public ResponseEntity<List<GisTersus>> getTersusAll() {
        List<GisTersus> listRest = restService.getTersusAll("","");
        return new ResponseEntity<List<GisTersus>>(listRest, HttpStatus.OK);
     }

	 @GetMapping("/tersus/{what}/{key}")
     public ResponseEntity<List<GisTersus>> getTersusKey(@PathVariable("what") String what, @PathVariable("key") String key) {
        List<GisTersus> listRest = restService.getTersusAll(what, key);
        return new ResponseEntity<List<GisTersus>>(listRest, HttpStatus.OK);
     }

	 @GetMapping("/tersus/detail/{key}")
     public ResponseEntity<List<GisTersusDetail>> getTersusDetail(@PathVariable("key") int key) {
        List<GisTersusDetail> listRest = restService.getTersusDetail(key);
        return new ResponseEntity<List<GisTersusDetail>>(listRest, HttpStatus.OK);
     }
	 
	 @GetMapping("/ship")
     public ResponseEntity<List<GisShip>> getShipAll() {
        List<GisShip> listRest = restService.getShipAll("","");
        return new ResponseEntity<List<GisShip>>(listRest, HttpStatus.OK);
     }	 	 
	 
	 @GetMapping("/ship/{what}/{key}")
     public ResponseEntity<List<GisShip>> getShipKey(@PathVariable("what") String what, @PathVariable("key") String key) {
        List<GisShip> listRest = restService.getShipAll(what,key);
        return new ResponseEntity<List<GisShip>>(listRest, HttpStatus.OK);
     }	 	 
	 
	 @GetMapping("/ship/detail/{key}")
     public ResponseEntity<List<GisShipDetail>> getShipDetail(@PathVariable("key") String key) {
        List<GisShipDetail> listRest = restService.getShipDetail(key);
        return new ResponseEntity<List<GisShipDetail>>(listRest, HttpStatus.OK);
     }	 
	 
	 @GetMapping("/search/{key}")
     public ResponseEntity<List<GisSearch>> getSearch(@PathVariable("key") String key) {
	    List<GisSearch> listRest = restService.getSearch(key);
        return new ResponseEntity<List<GisSearch>>(listRest, HttpStatus.OK);
     }	 
}
