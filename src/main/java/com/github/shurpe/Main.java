package com.github.shurpe;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public final class a {

    private final String b;
    private String c, d, e;
    private boolean f;
    private final List<g> g = new ArrayList<>();

    public a(String b) {
        this.b = b;
    }

    public a a(String c) {
        this.c = c;
        return this;
    }

    public a b(String d) {
        this.d = d;
        return this;
    }

    public a c(String e) {
        this.e = e;
        return this;
    }

    public a d(boolean f) {
        this.f = f;
        return this;
    }

    public a a(g h) {
        this.g.add(h);
        return this;
    }

    public void e() throws IOException {
        JsonObject i = new JsonObject();

        i.addProperty("content", this.c);
        i.addProperty("username", this.d);
        i.addProperty("avatar_url", this.e);
        i.addProperty("tts", this.f);

        if (!this.g.isEmpty()) {
            JsonArray j = new JsonArray();

            for (g k : this.g) {
                JsonObject l = new JsonObject();

                l.addProperty("title", k.a());
                l.addProperty("description", k.b());
                l.addProperty("url", k.c());

                if (k.d() != -1)
                    l.addProperty("color", k.d());

                g.m n = k.e();
                g.o p = k.f();
                g.q r = k.g();
                g.s t = k.h();
                List<g.u> v = k.i();

                if (n != null) {
                    JsonObject w = new JsonObject();
                    w.addProperty("text", n.a());
                    w.addProperty("icon_url", n.b());
                    l.add("footer", w);
                }

                if (p != null) {
                    JsonObject x = new JsonObject();
                    x.addProperty("url", p.a());
                    l.add("image", x);
                }

                if (r != null) {
                    JsonObject y = new JsonObject();
                    y.addProperty("url", r.a());
                    l.add("thumbnail", y);
                }

                if (t != null) {
                    JsonObject z = new JsonObject();
                    z.addProperty("name", t.a());
                    z.addProperty("url", t.b());
                    z.addProperty("icon_url", t.c());
                    l.add("author", z);
                }

                JsonArray aa = new JsonArray();
                for (g.u ab : v) {
                    JsonObject ac = new JsonObject();
                    ac.addProperty("name", ab.a());
                    ac.addProperty("value", ab.b());
                    ac.addProperty("inline", ab.c());
                    aa.add(ac);
                }
                l.add("fields", aa);

                j.add(l);
            }

            i.add("embeds", j);
        }

        try (CloseableHttpClient ad = HttpClients.createDefault()) {
            HttpPost ae = new HttpPost(this.b);
            ae.setHeader("Accept", "application/json");
            ae.setHeader("Content-type", "application/json");
            ae.setEntity(new StringEntity(i.toString()));

            ad.execute(ae);
        }
    }

    public static class g {

        private String a, b, c;
        private int d = -1;
        private m e;
        private q f;
        private o g;
        private s h;
        private final List<u> i = new ArrayList<>();

        public String a() { return a; }
        public String b() { return b; }
        public String c() { return c; }
        public int d() { return d; }
        public m e() { return e; }
        public q f() { return f; }
        public o g() { return g; }
        public s h() { return h; }
        public List<u> i() { return i; }

        public g a(String a) { this.a = a; return this; }
        public g b(String b) { this.b = b; return this; }
        public g c(String c) { this.c = c; return this; }
        public g d(int d) { this.d = d; return this; }
        public g e(String text, String icon) { this.e = new m(text, icon); return this; }
        public g f(String url) { this.f = new q(url); return this; }
        public g g(String url) { this.g = new o(url); return this; }
        public g h(String name, String url, String icon) { this.h = new s(name, url, icon); return this; }
        public g i(String name, String value, boolean inline) { this.i.add(new u(name, value, inline)); return this; }

        private static class m {
            private final String a, b;
            private m(String a, String b) { this.a = a; this.b = b; }
            private String a() { return a; }
            private String b() { return b; }
        }

        private static class q {
            private final String a;
            private q(String a) { this.a = a; }
            private String a() { return a; }
        }

        private static class o {
            private final String a;
            private o(String a) { this.a = a; }
            private String a() { return a; }
        }

        private static class s {
            private final String a, b, c;
            private s(String a, String b, String c) { this.a = a; this.b = b; this.c = c; }
            private String a() { return a; }
            private String b() { return b; }
            private String c() { return c; }
        }

        private static class u {
            private final String a, b;
            private final boolean c;
            private u(String a, String b, boolean c) { this.a = a; this.b = b; this.c = c; }
            private String a() { return a; }
            private String b() { return b; }
            private boolean c() { return c; }
        }
    }
}
