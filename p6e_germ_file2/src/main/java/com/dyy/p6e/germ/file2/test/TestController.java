package com.dyy.p6e.germ.file2.test;

import org.springframework.http.codec.multipart.FilePart;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.io.File;
import java.io.FileInputStream;
import java.util.*;

/**
 * @author lidashuang
 * @version 1.0
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @PostMapping("/start")
    public Mono<Object> start () {
        final String mark = UUID.randomUUID().toString().replace("-", "");
        final File f = new File("/Users/admin/Documents/p6e/p6e-test-file/" + mark);
        f.mkdirs();
        Map<String, String> a = new HashMap<>();
        a.put("mark", mark);
        return Mono.just(a);
    }

    @PostMapping("/upload")
    public String fpsc(@RequestPart("file") FilePart multipartFile, String chunk, String mark, ServerHttpResponse response) {
        try {
            System.out.println(chunk + "   ----   " + mark);
            File f = new File("/Users/admin/Documents/p6e/p6e-test-file/" + mark + "/" + chunk + ".chunk");
            f.createNewFile();
            multipartFile.transferTo(f);
            return DigestUtils.md5DigestAsHex(new FileInputStream(f));
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }

    }

    @PostMapping("/end")
    public List<String> end (String mark, ServerHttpResponse response) {
        final File f = new File("/Users/admin/Documents/p6e/p6e-test-file/" + mark);
        final List<String> fs = new ArrayList<>();
        if (f.listFiles() != null) {
            for (File file : Objects.requireNonNull(f.listFiles())) {
                fs.add(file.getName());
            }
        }
        return fs;
    }

}
