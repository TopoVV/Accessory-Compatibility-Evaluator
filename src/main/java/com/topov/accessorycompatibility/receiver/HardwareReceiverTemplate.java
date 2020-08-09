package com.topov.accessorycompatibility.receiver;

import com.topov.accessorycompatibility.assembler.HardwareAssembler;
import com.topov.accessorycompatibility.model.Hardware;
import com.topov.accessorycompatibility.net.JsoupClient;
import com.topov.accessorycompatibility.parser.SpecsGeneralizer;
import org.jsoup.nodes.Document;

import java.util.Map;

public abstract class HardwareReceiverTemplate<T extends Hardware> {
    protected JsoupClient client;
    protected SpecsGeneralizer generalize;
    protected HardwareAssembler assembler;

    public HardwareReceiverTemplate(JsoupClient client, SpecsGeneralizer generalize, HardwareAssembler assembler) {
        this.client = client;
        this.generalize = generalize;
        this.assembler = assembler;
    }

    public final T receiveHardwareComponent(String url) {
        final Document document = requestDom(url);
        final Map<String, String> specs = parseDom(document);
        final Map<String, String> generalized = generalizeSpecs(specs);
        return assembleHardware(generalized);
    }

    private Document requestDom(String url) {
        return client.requestDom(url);
    }

    private Map<String, String> generalizeSpecs(Map<String, String> specs) {
        return generalize.generalizeSpecifications(specs);
    }

    protected abstract Map<String, String> parseDom(Document document);
    protected abstract T assembleHardware(Map<String, String> specs);
}
