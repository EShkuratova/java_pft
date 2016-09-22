package ru.stqa.pft.soap;

import net.webservicex.GeoIP;
import net.webservicex.GeoIPService;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/**
 * Created by eshkuratova on 22.09.2016.
 */
public class GeoIpServiceTest {
    @Test
    public void testMyIP(){
        GeoIP ip = new GeoIPService().getGeoIPServiceSoap12().getGeoIP("93.92.196.22");
        assertEquals(ip.getCountryCode(),"RUS ");
    }

    @Test
    public void testInvalidIP(){
        GeoIP ip = new GeoIPService().getGeoIPServiceSoap12().getGeoIP("93.92.196.xxx");
        assertEquals(ip.getReturnCodeDetails(),"Invalid IP address");

    }
}
