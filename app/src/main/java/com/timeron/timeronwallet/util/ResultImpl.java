package com.timeron.timeronwallet.util;

import com.timeron.timeronwallet.service.entity.WalletRecord;
import com.timeron.timeronwallet.util.interf.AbstractResult;
import com.timeron.timeronwallet.util.interf.AvailableForResult;

/**
 * Created by Timeron on 2017-03-05.
 *
 */

public class ResultImpl extends AbstractResult {

    public ResultImpl(AvailableForResult object) {
        super(object);
    }
}
