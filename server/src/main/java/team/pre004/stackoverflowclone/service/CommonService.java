package team.pre004.stackoverflowclone.service;

import org.springframework.http.HttpHeaders;

public interface CommonService{
    HttpHeaders redirect(String uri);
    HttpHeaders redirect(String uri, String auth);
}
