package com.istudio.carmackfx.utils;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * Created by ACA on 2017-6-10.
 */
public class PairSet<L, R> extends HashSet<Pair<L, R>> {

    private final Map<L, R> rightMap = new HashMap<>();
    private final Map<R, L> leftMap = new HashMap<>();

    @Override
    public boolean add(Pair<L, R> val) {

        if(rightMap.containsKey(val.getLeft()) || leftMap.containsKey(val.getRight())) {

            return false;
        }

        if(super.add(val)) {

            rightMap.put(val.getLeft(), val.getRight());
            leftMap.put(val.getRight(), val.getLeft());

            return true;
        }

        return false;
    }

    @Override
    public boolean remove(Object o) {

        if(o instanceof Pair) {

            if(super.remove(o)) {

                Pair<L, R> val = (Pair<L, R>)o;

                rightMap.remove(val.getLeft());
                leftMap.remove(val.getRight());

                return true;
            }
        }

        return false;
    }

    @Override
    public void clear() {

        super.clear();
        rightMap.clear();
        leftMap.clear();
    }

    public R findRight(L left) {

        if(rightMap.containsKey(left)) {

            return rightMap.get(left);
        }

        return null;
    }

    public L findLeft(R right) {

        if(leftMap.containsKey(right)) {

            return leftMap.get(right);
        }

        return null;
    }
}
