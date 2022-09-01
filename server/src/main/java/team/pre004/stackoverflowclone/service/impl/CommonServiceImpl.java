package team.pre004.stackoverflowclone.service.impl;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import team.pre004.stackoverflowclone.service.CommonService;

import java.net.URI;


@Service
public class CommonServiceImpl implements CommonService {

    @Override
    public HttpHeaders redirect(String uri) {

        HttpHeaders headers = new HttpHeaders();

        String port = "8082";
        headers.setLocation(URI.create("http://localhost:" + port + uri));
        return headers;
    }

    @Override
    public HttpHeaders redirect(String uri, String auth) {
        HttpHeaders headers = new HttpHeaders();
        String port = "8083";

        headers.setBearerAuth(auth);
        headers.setLocation(URI.create("http://localhost:" + port + uri));
        return headers;
    }
}
