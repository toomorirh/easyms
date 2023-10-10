package com.rhcsa.easyms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.rhcsa.easyms.client.OmniClient;
import com.rhcsa.easyms.model.Tutorial;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import reactor.core.publisher.Mono;

@CrossOrigin
@RestController
@RequestMapping("/api")
@Tag(name = "Omni WebClient", description = "バックエンドのAPIをコールします。（※このAPIは擬似的に動作するように実装/設定してあります。必要に応じてコードを変更してください）<br>" 
+ " デフォルトはlocalhost:8080 Simple CRUD APIと同等のAPIをこのサービスからコールします。 ")
public class OmniClientController {

	@Autowired
	private OmniClient omniClient;

    @Operation(summary = "バックエンドへ取得のAPIコール", description = "API(GET:)をコールします。Id指定")
	@GetMapping("/client/{id}")
    public Mono<ResponseEntity<Tutorial>> getDataById(@PathVariable String id){
        Mono<Tutorial> user = omniClient.getOmniData(id);
        return user.map( u -> ResponseEntity.ok(u))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @Operation(summary = "バックエンドへ登録のAPIコール", description = "API(POST:)をコールします")
	@PostMapping("/client/")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Tutorial> createData(@RequestBody Tutorial data){
        return omniClient.createOmniData(data);
    }
}
