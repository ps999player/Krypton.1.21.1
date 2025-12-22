package com.github.shurpe;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

public final class a {

    private final String a;
    private String b, c, d;
    private boolean e;
    private final List<b> f = new ArrayList<>();

    public a(String a) {
        this.a = a;
    }

    public a a(String b) {
        this.b = b;
        return this;
    }

    public a b(String c) {
        this.c = c;
        return this;
    }

    public a c(String d) {
        this.d = d;
        return this;
    }

    public a a(boolean e) {
        this.e = e;
        return this;
    }

    public a a(b g) {
        this.f.add(g);
        return this;
    }

    public void a() throws IOException, InterruptedException {
        JsonObject g = new JsonObject();
        g.addProperty("content", b);
        g.addProperty("username", c);
        g.addProperty("avatar_url", d);
        g.addProperty("tts", e);

        if (!f.isEmpty()) {
            JsonArray h = new JsonArray();
            for (b i : f) {
                h.add(i.a());
            }
            g.add("embeds", h);
        }

        HttpRequest j = HttpRequest.newBuilder()
                .uri(URI.create(a))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(g.toString()))
                .build();

        HttpClient.newHttpClient()
                .send(j, HttpResponse.BodyHandlers.discarding());
    }

    // ============== EMBED ==============

    public static final class b {
        private String a, b, c;
        private int d = -1;
        private final List<c> e = new ArrayList<>();

        public b a(String a) { this.a = a; return this; }
        public b b(String b) { this.b = b; return this; }
        public b c(String c) { this.c = c; return this; }
        public b a(int d) { this.d = d; return this; }

        public b a(String a, String b, boolean c) {
            this.e.add(new c(a, b, c));
            return this;
        }

        private JsonObject a() {
            JsonObject f = new JsonObject();
            f.addProperty("title", a);
            f.addProperty("description", b);
            f.addProperty("url", c);

            if (d != -1) f.addProperty("color", d);

            if (!e.isEmpty()) {
                JsonArray g = new JsonArray();
                for (c h : e) {
                    g.add(h.a());
                }
                f.add("fields", g);
            }

            return f;
        }
    }

    private record c(String a, String b, boolean c) {
        JsonObject a() {
            JsonObject d = new JsonObject();
            d.addProperty("name", a);
            d.addProperty("value", b);
            d.addProperty("inline", c);
            return d;
        }
    }
}
