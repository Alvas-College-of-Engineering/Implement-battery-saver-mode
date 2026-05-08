package com.example.batterysaver;

public final class DashboardRenderer {
    public String render(BatterySaverSnapshot snapshot) {
        BatteryStatus status = snapshot.status();
        PowerProfile profile = snapshot.profile();
        String modeClass = profile.saverMode() ? "saving" : "balanced";
        String saverText = profile.saverMode() ? "ACTIVE" : "OFF";
        int ring = status.level() * 360 / 100;

        return """
                <!doctype html>
                <html lang="en">
                <head>
                    <meta charset="utf-8">
                    <meta name="viewport" content="width=device-width, initial-scale=1">
                    <meta http-equiv="refresh" content="%d">
                    <title>Battery Saver System</title>
                    <style>%s</style>
                </head>
                <body class="%s">
                    <main class="shell">
                        <section class="hero">
                            <div>
                                <p class="eyebrow">Java Dynamic Web Project</p>
                                <h1>Battery Saver System</h1>
                                <p class="sub">Automatically monitors the device battery, activates saver mode, and displays the adjusted power settings.</p>
                            </div>
                            <div class="status-pill">%s</div>
                        </section>

                        <section class="dashboard">
                            <article class="battery-card">
                                <div class="ring" style="background: conic-gradient(var(--accent) %ddeg, rgba(255,255,255,.16) 0deg);">
                                    <div class="ring-core">
                                        <strong>%d%%</strong>
                                        <span>%s</span>
                                    </div>
                                </div>
                                <div class="reading">
                                    <h2>%s</h2>
                                    <p>%s</p>
                                </div>
                            </article>

                            <article class="mode-card">
                                <span class="label">Saver Mode</span>
                                <strong>%s</strong>
                                <p>%s</p>
                            </article>

                            <article class="settings-card">
                                %s
                            </article>
                        </section>

                        <section class="timeline">
                            <div><span>Checked</span><strong>%s</strong></div>
                            <div><span>Refresh</span><strong>Every %d seconds</strong></div>
                            <div><span>Engine</span><strong>Java HttpServer + OSHI</strong></div>
                        </section>
                    </main>
                </body>
                </html>
                """.formatted(
                profile.refreshSeconds(),
                css(),
                modeClass,
                saverText,
                ring,
                status.level(),
                status.available() ? "battery level" : "estimated",
                profile.modeName(),
                status.powerStateText(),
                profile.modeName(),
                profile.reason(),
                settings(profile),
                status.checkedAtText(),
                profile.refreshSeconds());
    }

    private String settings(PowerProfile profile) {
        return """
                <span class="label">Adjusted Settings</span>
                <div class="setting"><span>Screen brightness</span><strong>%d%%</strong></div>
                <div class="setting"><span>Background sync</span><strong>%s</strong></div>
                <div class="setting"><span>Animations</span><strong>%s</strong></div>
                """.formatted(
                profile.screenBrightness(),
                profile.backgroundSync() ? "Enabled" : "Limited",
                profile.animations() ? "Enabled" : "Reduced");
    }

    private String css() {
        return """
                :root{--bg:#101417;--panel:#182024;--panel2:#20292e;--text:#f5f7f3;--muted:#aeb8ad;--accent:#73d677;--warn:#ffcc66;--danger:#ff6b5f;--line:rgba(255,255,255,.11)}
                *{box-sizing:border-box}body{margin:0;min-height:100vh;font-family:Arial,Helvetica,sans-serif;background:radial-gradient(circle at top left,#23302b,#101417 38%,#0d1113);color:var(--text)}
                body.saving{--accent:#ffcc66;background:radial-gradient(circle at top left,#3a2e18,#111414 42%,#0b0d0e)}
                .shell{width:min(1120px,92vw);margin:0 auto;padding:38px 0}
                .hero{display:flex;align-items:flex-end;justify-content:space-between;gap:24px;padding:18px 0 28px;border-bottom:1px solid var(--line)}
                .eyebrow{margin:0 0 10px;color:var(--accent);font-weight:700;text-transform:uppercase;font-size:12px;letter-spacing:.08em}
                h1{margin:0;font-size:clamp(34px,6vw,70px);line-height:1;letter-spacing:0}
                .sub{max-width:650px;color:var(--muted);font-size:18px;line-height:1.6;margin:18px 0 0}
                .status-pill{border:1px solid var(--line);background:rgba(255,255,255,.07);border-radius:999px;padding:14px 20px;font-weight:800;color:var(--accent)}
                .dashboard{display:grid;grid-template-columns:1.4fr .9fr 1fr;gap:18px;margin-top:28px}
                article{background:linear-gradient(145deg,var(--panel),var(--panel2));border:1px solid var(--line);border-radius:8px;box-shadow:0 20px 50px rgba(0,0,0,.28)}
                .battery-card{display:flex;align-items:center;gap:28px;padding:28px}.ring{width:220px;aspect-ratio:1;border-radius:50%;display:grid;place-items:center;flex:0 0 auto}.ring-core{width:164px;aspect-ratio:1;border-radius:50%;display:grid;place-items:center;background:#121719;text-align:center}.ring-core strong{font-size:48px}.ring-core span{margin-top:-34px;color:var(--muted);font-size:13px;text-transform:uppercase}
                .reading h2{font-size:34px;margin:0 0 12px}.reading p,.mode-card p{color:var(--muted);line-height:1.6;margin:0}
                .mode-card,.settings-card{padding:24px}.label{display:block;color:var(--muted);text-transform:uppercase;font-size:12px;font-weight:700;margin-bottom:16px}.mode-card strong{display:block;font-size:42px;color:var(--accent);margin-bottom:14px}
                .setting{display:flex;align-items:center;justify-content:space-between;gap:16px;padding:15px 0;border-top:1px solid var(--line)}.setting span{color:var(--muted)}.setting strong{font-size:20px}
                .timeline{display:grid;grid-template-columns:repeat(3,1fr);gap:18px;margin-top:18px}.timeline div{border:1px solid var(--line);border-radius:8px;padding:18px;background:rgba(255,255,255,.05)}.timeline span{display:block;color:var(--muted);font-size:12px;text-transform:uppercase;margin-bottom:8px}.timeline strong{font-size:16px}
                @media (max-width:900px){.hero{display:block}.status-pill{display:inline-block;margin-top:20px}.dashboard,.timeline{grid-template-columns:1fr}.battery-card{display:block}.ring{margin:0 auto 24px;width:min(220px,72vw)}}
                """;
    }
}
