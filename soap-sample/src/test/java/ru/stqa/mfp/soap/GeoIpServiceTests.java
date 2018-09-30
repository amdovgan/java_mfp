package ru.stqa.mfp.soap;

import com.lavasoft.GeoIPService;
import com.lavasoft.GeoIPServiceSoap;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class GeoIpServiceTests {

  //@Test
  public void testMyIp() {
    String ipLocation = new GeoIPService().getGeoIPServiceSoap12().getIpLocation("46.39.46.227");
    System.out.println(ipLocation);
  }

  @Test
  public void testIvnalidIp() {
    String ipLocation = new GeoIPService().getGeoIPServiceSoap12().getIpLocation("46.39.46.xxx");
    System.out.println(ipLocation);
  }


}
