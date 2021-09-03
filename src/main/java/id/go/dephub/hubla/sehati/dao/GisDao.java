package id.go.dephub.hubla.sehati.dao;

import java.util.List;

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

public interface GisDao {
	List<GisPort> getPortAll(String what, String key);
	List<GisPortDetail> getPortDetail(String key);
	List<GisSbnp> getSbnpAll();
	List<GisSbnpDetail> getSbnpDetail(int key);
	List<GisSrop> getSropAll();
	List<GisSropDetail> getSropDetail(int key);
	List<GisVts> getVtsAll();
	List<GisVtsDetail> getVtsDetail(int key);
	List<GisTersus> getTersusAll(String what, String key);
	List<GisTersusDetail> getTersusDetail(int key);
	List<GisShip> getShipAll(String what, String key);
	List<GisShipDetail> getShipDetail(String key);
	List<GisSearch> getSearch(String key);
}
