package com.wracle.cakemgr.cakemanager.io;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wracle.cakemgr.cakemanager.io.entity.CakeEntity;
import com.wracle.cakemgr.cakemanager.io.entity.UserEntity;
import com.wracle.cakemgr.cakemanager.io.repository.CakeRepository;
import com.wracle.cakemgr.cakemanager.io.repository.UserRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
    @Autowired
    private UserRepository userRepository;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        UserEntity userEntity = new UserEntity();
        userEntity.setEmailId("anand@anand.com");
        userEntity.setEncryptedPassword(bCryptPasswordEncoder.encode("admin"));
        userRepository.save(userEntity);
        loadDateFromGitJson();
    }

    private void loadDateFromGitJson() throws IOException {
        String cakeDataJsonUrl = "https://gist.githubusercontent.com/hart88/198f29ec5114a3ec3460/raw/8dd19a88f9b8d24c23d9960f3300d0c917a4f07c/cake.json";
        ObjectMapper mapper = new ObjectMapper();
        cakeToLoad[] cakeToLoadArr = mapper.readValue(new URL(cakeDataJsonUrl), cakeToLoad[].class);
        List<CakeEntity> cakeEntityLst = Arrays.stream(cakeToLoadArr)
                .map(cakeToLoad -> {
                    CakeEntity cakeEntity = new CakeEntity();
                    cakeEntity.setName(cakeToLoad.getTitle());
                    cakeEntity.setDescription(cakeToLoad.getDesc());
                    cakeEntity.setImageUrl(cakeToLoad.getImage());
                    return cakeEntity;
                })
                .distinct()
                .collect(Collectors.toList());
        cakeEntityLst.stream().forEach(cakeEntity -> cakeRepository.save(cakeEntity) );
    }

    @Data
    static class cakeToLoad {
        private String title;
        private String desc;
        private String image;
    }
}
