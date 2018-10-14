/*
 * Copyright (c) 2014, Oracle America, Inc.
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 *  * Redistributions of source code must retain the above copyright notice,
 *    this list of conditions and the following disclaimer.
 *
 *  * Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in the
 *    documentation and/or other materials provided with the distribution.
 *
 *  * Neither the name of Oracle nor the names of its contributors may be used
 *    to endorse or promote products derived from this software without
 *    specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF
 * THE POSSIBILITY OF SUCH DAMAGE.
 */

package io.github.ma1uta.rd.json;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.ParserConfig;
import com.dslplatform.json.DslJson;
import com.dslplatform.json.runtime.Settings;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jsoniter.JsonIterator;
import com.jsoniter.extra.JacksonCompatibilityMode;
import com.jsoniter.spi.DecodingMode;
import com.jsoniter.spi.JsoniterSpi;
import org.eclipse.yasson.JsonBindingProvider;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.infra.Blackhole;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.StringReader;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeUnit;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.json.bind.JsonbConfig;

public class MyBenchmark {

    @State(Scope.Thread)
    public static class SetupState {

        @Setup(Level.Trial)
        public void setup() {
            ParserConfig.getGlobalInstance().putDeserializer(Event.class, new io.github.ma1uta.rd.json.fastjson.EventDeserializer());

            SimpleModule module = new SimpleModule();
            module.addDeserializer(Event.class, new io.github.ma1uta.rd.json.jackson.EventDeserializer());

            jackson = new ObjectMapper();
            jackson.registerModule(module);
            jackson.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

            JsoniterSpi.registerExtension(new JacksonCompatibilityMode.Builder().build());
            JsoniterSpi.registerTypeDecoder(Event.class, new io.github.ma1uta.rd.json.jsoniter.EventDeserializer());
            JsonIterator.setMode(DecodingMode.REFLECTION_MODE);
        }

        public final String ORIGIN_F2 = "{\"type\":\"f\",\"content\":{\"field\":\"data\"},\"room_id\":\"123\"}";

        public final byte[] input = ORIGIN_F2.getBytes(StandardCharsets.UTF_8);

        public InputStream inputStream = new ByteArrayInputStream(ORIGIN_F2.getBytes(StandardCharsets.UTF_8));

        public Reader reader = new StringReader(ORIGIN_F2);

        public DslJson<?> dslJson = new DslJson<>(Settings.withRuntime().includeServiceLoader());

        public Gson gson = new GsonBuilder().registerTypeAdapter(Event.class, new io.github.ma1uta.rd.json.gson.EventDeserializer())
            .create();

        public ObjectMapper jackson;

        public Jsonb jsonb = JsonbBuilder.newBuilder(JsonBindingProvider.provider())
            .withConfig(new JsonbConfig().withDeserializers(new io.github.ma1uta.rd.json.jsonb.EventDeserializer())).build();
    }

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    public void dsljson(SetupState setup, Blackhole blackhole) throws IOException {
        blackhole.consume(setup.dslJson.deserialize(Event.class, setup.input, setup.input.length));
    }

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    public void fastjson(SetupState setup, Blackhole blackhole) throws IOException {
        blackhole.consume(JSON.parseObject(setup.input, Event.class));
    }

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    public void gson(SetupState setup, Blackhole blackhole) throws IOException {
        blackhole.consume(setup.gson.fromJson(setup.reader, Event.class));
    }

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    public void jackson(SetupState setup, Blackhole blackhole) throws IOException {
        blackhole.consume(setup.jackson.readValue(setup.input, Event.class));
    }

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    public void jsonb(SetupState setup, Blackhole blackhole) throws IOException {
        blackhole.consume(setup.jsonb.fromJson(setup.ORIGIN_F2, Event.class));
    }

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    public void jsoniter(SetupState setup, Blackhole blackhole) throws IOException {
        blackhole.consume(JsonIterator.deserialize(setup.input, Event.class));
    }
}
