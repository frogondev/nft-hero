package com.frogbyhinter.frognft.web.rest;

import com.frogbyhinter.frognft.aspect.annotation.FrogNftApiAuth;
import com.frogbyhinter.frognft.client.cypress.CypressClient;
import com.frogbyhinter.frognft.service.FRCMintService;
import com.frogbyhinter.frognft.service.FRCService;
import com.frogbyhinter.frognft.service.FRCTokenUriService;
import com.frogbyhinter.frognft.web.model.req.FRCMintReqBody;
import com.frogbyhinter.frognft.web.model.res.TokenUriResponse;
import com.frogbyhinter.frognft.web.support.FrogNftRestSupport;
import io.swagger.annotations.ApiImplicitParam;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;

@RestController
public class FrogCertificateRest extends FrogNftRestSupport {

    private final FRCMintService frcMintService;
    private final FRCTokenUriService frcTokenUriService;
    private final FRCService frcService;

    public FrogCertificateRest(FRCMintService frcMintService,
                               FRCTokenUriService frcTokenUriService,
                               FRCService frcService
    ) {
        this.frcMintService = frcMintService;
        this.frcTokenUriService = frcTokenUriService;
        this.frcService = frcService;
    }

    /**
     * Tokeun URI Data 조회
     *
     * @param tokenId
     * @return
     */
    @GetMapping("/nft/frc/{tokenId}")
    public TokenUriResponse getFrogCertificateTokenUriDataByTokenId(@PathVariable Long tokenId) {
        return frcTokenUriService.getTokenUriByTokenId(tokenId);
    }

    /**
     * 토큰 발행
     *
     * @param reqBody
     * @return
     */
    @ApiImplicitParam(
            example = "",
            name = "X-HEADER-NFT-KEY",
            paramType = "header",
            dataType = "string",
            dataTypeClass = String.class,
            value = ""
    )
    @PostMapping("/nft/frogc/mint")
    @FrogNftApiAuth
    public ResponseEntity mintFrogCertificateToken(@RequestBody FRCMintReqBody reqBody) {
        return success(
                frcMintService.mint(reqBody)
        );
    }

    @ApiImplicitParam(
            example = "",
            name = "X-HEADER-NFT-KEY",
            paramType = "header",
            dataType = "string",
            dataTypeClass = String.class,
            value = ""
    )
    @GetMapping("/nft/{walletAddress}/frc")
    @FrogNftApiAuth
    public ResponseEntity getFrcTokenListByAddress(
            @PathVariable String walletAddress,
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "size", defaultValue = "10") int size
    ) {
        return tokenInventoryPageResponse(
                frcService.getFrcTokenListByAddress(walletAddress, page, size)
        );
    }
}
