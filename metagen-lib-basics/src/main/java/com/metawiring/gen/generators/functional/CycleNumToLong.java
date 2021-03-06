package com.metawiring.gen.generators.functional;

import com.metawiring.gen.metagenapi.Generator;

/**
 * This is here for completeness. It's nothing but an identity function, only
 * named in a way that it can be found.
 */
public class CycleNumToLong implements Generator<Long> {
    @Override
    public Long get(long input) {
        return input;
    }
}
