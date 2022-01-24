package com.wracle.cakemgr.cakemanager.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wracle.cakemgr.cakemanager.repository.dao.CakeDao;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class DataLoader implements ApplicationRunner {

    @Autowired
    private CakeRepository cakeRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        /*CakeDao cakeDao = new CakeDao();
        cakeDao.setName("my cake 1");
        cakeDao.setDescription("my cake desc 1");
        cakeDao.setImageUrl("my cake url 1");
        cakeRepository.save(cakeDao);*/
        loadDateFromGitJson();
    }

    private void loadDateFromGitJson() throws IOException {
        String cakeDataJsonUrl = "https://gist.githubusercontent.com/hart88/198f29ec5114a3ec3460/raw/8dd19a88f9b8d24c23d9960f3300d0c917a4f07c/cake.json";
        ObjectMapper mapper = new ObjectMapper();
        cakeToLoad[] cakeToLoadArr = mapper.readValue(new URL(cakeDataJsonUrl), cakeToLoad[].class);
        List<CakeDao> cakeDaoLst = Arrays.stream(cakeToLoadArr)
                .map(cakeToLoad -> {
                    CakeDao cakeDao = new CakeDao();
                    cakeDao.setName(cakeToLoad.getTitle());
                    cakeDao.setDescription(cakeToLoad.getDesc());
                    cakeDao.setImageUrl(cakeToLoad.getImage());
                    return cakeDao;
                })
                .distinct()
                .collect(Collectors.toList());
        cakeDaoLst.stream().forEach(cakeDao -> cakeRepository.save(cakeDao) );
    }

    @Data
    static class cakeToLoad {
        private String title;
        private String desc;
        private String image;
    }
}
