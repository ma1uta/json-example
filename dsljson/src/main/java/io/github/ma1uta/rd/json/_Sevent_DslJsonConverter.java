package io.github.ma1uta.rd.json;

import com.dslplatform.json.Configuration;
import com.dslplatform.json.DslJson;
import com.dslplatform.json.JsonReader;
import com.dslplatform.json.JsonWriter;
import com.dslplatform.json.SerializationException;
import com.dslplatform.json.StringConverter;
import com.dslplatform.json.runtime.FormatConverter;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.charset.Charset;

public class _Sevent_DslJsonConverter implements Configuration {
    private static final Charset utf8 = Charset.forName("UTF-8");

    @Override
    public void configure(DslJson dslJson) {
        ObjectFormatConverter objectConverter = new ObjectFormatConverter(dslJson);
        dslJson.registerBinder(Sevent.class, objectConverter);
        dslJson.registerReader(Sevent.class, objectConverter);
        dslJson.registerWriter(Sevent.class, objectConverter);
    }

    public final static class ObjectFormatConverter implements FormatConverter<Sevent>, JsonReader.BindObject<Sevent> {
        private final boolean alwaysSerialize;
        private final DslJson dslJson;
        private final JsonReader.ReadObject<SecondContent> reader_content;
        private final JsonWriter.WriteObject<SecondContent> writer_content;

        ObjectFormatConverter(DslJson dslJson) {
            this.alwaysSerialize = !dslJson.omitDefaults;
            this.dslJson = dslJson;
            Type manifest_content = SecondContent.class;
            this.reader_content = dslJson.tryFindReader(manifest_content);
            if (reader_content == null) {
                throw new SerializationException(
                    "Unable to find reader for " + manifest_content + ". Enable runtime conversion by initializing DslJson with new DslJson<>(Settings.withRuntime().includeServiceLoader())");
            }
            this.writer_content = dslJson.tryFindWriter(manifest_content);
            if (writer_content == null) {
                throw new SerializationException(
                    "Unable to find writer for " + manifest_content + ". Enable runtime conversion by initializing DslJson with new DslJson<>(Settings.withRuntime().includeServiceLoader())");
            }
        }

        public Sevent read(JsonReader reader) throws IOException {
            if (reader.wasNull()) {
                return null;
            }
            return bind(reader, new Sevent());
        }

        private static final byte[] quoted_stateKey = "\"stateKey\":".getBytes(utf8);
        private static final byte[] name_stateKey = "stateKey".getBytes(utf8);
        private static final byte[] quoted_content = ",\"content\":".getBytes(utf8);
        private static final byte[] name_content = "content".getBytes(utf8);

        public final void write(JsonWriter writer, Sevent instance) {
            if (instance == null) {
                writer.writeNull();
            } else {
                writer.writeByte((byte) '{');
                if (alwaysSerialize) {
                    writeContentFull(writer, instance);
                    writer.writeByte((byte) '}');
                } else if (writeContentMinimal(writer, instance)) {
                    writer.getByteBuffer()[writer.size() - 1] = '}';
                } else {
                    writer.writeByte((byte) '}');
                }
            }
        }

        public void writeContentFull(JsonWriter writer, Sevent instance) {
            writer.writeAscii(quoted_stateKey);
            if (instance.getStateKey() == null) {
                writer.writeNull();
            } else {
                StringConverter.serialize(instance.getStateKey(), writer);
            }
            writer.writeAscii(quoted_content);
            if (instance.getContent() == null) {
                writer.writeNull();
            } else {
                writer_content.write(writer, instance.getContent());
            }
        }

        public boolean writeContentMinimal(JsonWriter writer, Sevent instance) {
            boolean hasWritten = false;
            if (instance.getStateKey() != null) {
                writer.writeByte((byte) '"');
                writer.writeAscii(name_stateKey);
                writer.writeByte((byte) '"');
                writer.writeByte((byte) ':');
                StringConverter.serialize(instance.getStateKey(), writer);
                writer.writeByte((byte) ',');
                hasWritten = true;
            }
            if (instance.getContent() != null) {
                writer.writeByte((byte) '"');
                writer.writeAscii(name_content);
                writer.writeByte((byte) '"');
                writer.writeByte((byte) ':');
                writer_content.write(writer, instance.getContent());
                writer.writeByte((byte) ',');
                hasWritten = true;
            }
            return hasWritten;
        }

        public Sevent bind(JsonReader reader, Sevent instance) throws IOException {
            if (reader.last() != '{') {
                throw new IOException("Expecting '{' " + reader.positionDescription() + ". Found " + (char) reader.last());
            }
            reader.getNextToken();
            bindContent(reader, instance);
            return instance;
        }

        public Sevent readContent(JsonReader reader) throws IOException {
            Sevent instance = new Sevent();
            bindContent(reader, instance);
            return instance;
        }

        public void bindContent(JsonReader reader, Sevent instance) throws IOException {
            if (reader.last() == '}') {
                return;
            }
            if (reader.fillNameWeakHash() != 842 || !reader.wasLastName(name_stateKey)) {
                bindSlow(reader, instance, 0);
                return;
            }
            reader.getNextToken();
            if (reader.wasNull()) {
                instance.setStateKey(null);
            } else {
                instance.setStateKey(StringConverter.deserialize(reader));
            }
            if (reader.getNextToken() == '}') {
                return;
            }
            if (reader.last() != ',') {
                throw new IOException("Expecting ',' " + reader.positionDescription() + ". Found: " + (char) reader.last());
            } else {
                reader.getNextToken();
            }
            if (reader.fillNameWeakHash() != 763 || !reader.wasLastName(name_content)) {
                bindSlow(reader, instance, 1);
                return;
            }
            reader.getNextToken();
            instance.setContent(reader_content.read(reader));
            if (reader.getNextToken() != '}') {
                if (reader.last() == ',') {
                    reader.getNextToken();
                    reader.fillNameWeakHash();
                    bindSlow(reader, instance, 2);
                }
                if (reader.last() != '}') {
                    throw new IOException("Expecting '}' " + reader.positionDescription() + ". Found " + (char) reader.last());
                }
            }
        }

        private void bindSlow(JsonReader reader, Sevent instance, int index) throws IOException {
            switch (reader.getLastHash()) {
                case -1866546238:
                    reader.getNextToken();
                    instance.setContent(reader_content.read(reader));
                    reader.getNextToken();
                    break;
                case 1955822765:
                    reader.getNextToken();
                    if (reader.wasNull()) {
                        instance.setStateKey(null);
                    } else {
                        instance.setStateKey(StringConverter.deserialize(reader));
                    }
                    reader.getNextToken();
                    break;
                default:
                    reader.getNextToken();
                    reader.skip();
            }
            while (reader.last() == ',') {
                reader.getNextToken();
                switch (reader.fillName()) {
                    case -1866546238:
                        reader.getNextToken();
                        instance.setContent(reader_content.read(reader));
                        reader.getNextToken();
                        break;
                    case 1955822765:
                        reader.getNextToken();
                        if (reader.wasNull()) {
                            instance.setStateKey(null);
                        } else {
                            instance.setStateKey(StringConverter.deserialize(reader));
                        }
                        reader.getNextToken();
                        break;
                    default:
                        reader.getNextToken();
                        reader.skip();
                }
            }
            if (reader.last() != '}') {
                throw new IOException("Expecting '}' " + reader.positionDescription() + ". Found " + (char) reader.last());
            }
        }
    }
}
