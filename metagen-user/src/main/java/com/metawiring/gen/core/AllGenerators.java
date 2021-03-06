package com.metawiring.gen.core;

import com.metawiring.gen.metagenapi.Generator;
import com.metawiring.gen.metagenapi.GeneratorLibrary;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class AllGenerators implements GeneratorLibrary {

    private static AllGenerators instance = new AllGenerators();
    private List<GeneratorLibrary> libraries = GeneratorLibraryFinder.getAll();

    private AllGenerators() {
    }

    public static AllGenerators get() {
        return instance;
    }

    @Override
    public String getLibraryName() {
        return "ALL";
    }

    @Override
    public <T> Optional<Generator<T>> getGenerator(String spec) {
        Optional<Generator<T>> generator = null;
        for (GeneratorLibrary library : libraries) {
            generator = library.getGenerator(spec);
            if ( generator != null ) {
                return generator;
            }
        }
        throw new RuntimeException("Unable to find spec:" + spec + " in any library, searched in " + toString());
    }

    @Override
    public List<String> getGeneratorNames() {
        List<String> genNames = new ArrayList<>();
        for (GeneratorLibrary library : libraries) {
            List<String> libGenNames = library.getGeneratorNames().stream()
                    .map(genName -> library.getLibraryName() + "::" + genName)
                    .collect(Collectors.toList());
            genNames.addAll(libGenNames);
        }
        genNames.sort(Comparator.naturalOrder());
        return genNames;
    }

    public String toString() {
        return AllGenerators.class.getSimpleName() + ":"
                + libraries.stream().map(GeneratorLibrary::getLibraryName).collect(Collectors.joining(",","[","]"));
    }

}
