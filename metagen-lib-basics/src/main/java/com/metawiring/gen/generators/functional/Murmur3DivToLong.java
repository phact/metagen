/*
 *
 *       Copyright 2015 Jonathan Shook
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 *
 */

package com.metawiring.gen.generators.functional;

import com.metawiring.gen.metagenapi.Generator;
import de.greenrobot.common.hash.Murmur3F;

public class Murmur3DivToLong implements Generator<Long> {

    private Murmur3F murmur3f = new Murmur3F();
    private DivideToLong longDivSequenceGenerator;

    public Murmur3DivToLong(long divisor) {
        this.longDivSequenceGenerator = new DivideToLong(divisor);
    }
    public Murmur3DivToLong(String divisor) {
        this(Long.valueOf(divisor));
    }

    @Override
    public Long get(long input) {
        long divided= longDivSequenceGenerator.get(input);
        murmur3f.update((int) (divided % Integer.MAX_VALUE));
        return murmur3f.getValue();
    }
}
