package com.deloitte.nextgen.framework.commons.support;

import java.util.Map;
import java.util.Objects;
import java.util.function.Function;

public final class MoreFunctions {

    private MoreFunctions(){
        throw new AssertionError("Creating instance of "+MoreFunctions.class.getName()+" is not allowed.");
    }

    public static <K, V> Function<K, V> toMapFunction(Map<K, V> map){
        return new MapFunction<>(map);
    }

    private static class MapFunction<K, V> implements Function<K, V>{
        private final Map<K, V> map;
        private MapFunction(Map<K, V> map){
            this.map = Objects.requireNonNull(map, "Map should not be null");
        }
        @Override
        public V apply(K k) {
            return map.get(k);
        }
    }
}
