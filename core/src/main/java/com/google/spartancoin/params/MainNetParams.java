/*
 * Copyright 2013 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.spartancoin.params;

import com.google.spartancoin.core.Sha256Hash;

import com.google.spartancoin.core.CoinParams;
import com.google.spartancoin.core.NetworkParameters;
import org.spongycastle.util.encoders.Hex;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.google.common.base.Preconditions.checkState;

/**
 * Parameters for the main production network on which people trade goods and services.
 */
public class MainNetParams extends NetworkParameters {
    private static final Logger log = LoggerFactory.getLogger(MainNetParams.class);

    public MainNetParams() {
        super();
        interval = CoinParams.INTERVAL;
        targetTimespan = CoinParams.TARGET_TIMESPAN;
        //proofOfWorkLimit = Utils.decodeCompactBits(CoinParams.proofOfWorkLimit);
        proofOfWorkLimit = CoinParams.proofOfWorkLimit;
        dumpedPrivateKeyHeader = 128 + CoinParams.addressHeader;
        addressHeader = CoinParams.addressHeader;
        p2shHeader = CoinParams.p2shHeader;
        acceptableAddressCodes = new int[] { addressHeader, p2shHeader };
        port = CoinParams.Port;
        packetMagic = CoinParams.packetMagic;
        genesisBlock.setDifficultyTarget(CoinParams.genesisBlockDifficultyTarget);
        genesisBlock.setTime(CoinParams.genesisBlockTime);
        genesisBlock.setNonce(CoinParams.genesisBlockNonce);

        //genesisTxInBytes = CoinParams.genesisTxInBytes;
        //genesisTxOutBytes = CoinParams.genesisTxOutBytes;
        genesisBlock.setMerkleRoot(new Sha256Hash("842c7513b6410bfe27ef309ae9d4326e2a3e4c29a78fb2c9336ddd0d1da511da"));

        id = ID_MAINNET;
        subsidyDecreaseBlockCount = CoinParams.subsidyDecreaseBlockCount;
        spendableCoinbaseDepth = CoinParams.spendableCoinbaseDepth;
        String genesisHash = genesisBlock.getHashAsString();
        log.info("Genesis Block (complete): " + genesisBlock.toString()) ;
        checkState(genesisHash.equals(CoinParams.genesisHash), genesisHash);
        log.info("Genesis Block checked successfully ") ;
        alertSigningKey = Hex.decode(CoinParams.SATOSHI_KEY);


        // This contains (at a minimum) the blocks which are not BIP30 compliant. BIP30 changed how duplicate
        // transactions are handled. Duplicated transactions could occur in the case where a coinbase had the same
        // extraNonce and the same outputs but appeared at different heights, and greatly complicated re-org handling.
        // Having these here simplifies block connection logic considerably.
        //checkpoints.put(91722, new Sha256Hash("00000000000271a2dc26e7667f8419f2e15416dc6955e5a6c6cdf3f2574dd08e"));
        //checkpoints.put(91812, new Sha256Hash("00000000000af0aed4792b1acee3d966af36cf5def14935db8de83d6f9306f2f"));
        //checkpoints.put(91842, new Sha256Hash("00000000000a4d0a398161ffc163c503763b1f4360639393e0e4c8e300e0caec"));
        //checkpoints.put(91880, new Sha256Hash("00000000000743f190a18c5577a3c2d2a1f610ae9601ac046a38084ccb7cd721"));
        //checkpoints.put(200000, new Sha256Hash("000000000000034a7dedef4a161fa058a2d67a173a90155f3a2fe6fc132e0ebf"));

        dnsSeeds = CoinParams.dnsSeeds;
        CoinParams.initCheckpoints(checkpoints);
    }

    private static MainNetParams instance;
    public static synchronized MainNetParams get() {
        if (instance == null) {
            instance = new MainNetParams();
        }
        return instance;
    }

    public String getPaymentProtocolId() {
        return PAYMENT_PROTOCOL_ID_MAINNET;
    }
}
