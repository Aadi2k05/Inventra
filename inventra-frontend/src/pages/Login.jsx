import { useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import api, { errMsg } from "../services/api";

export default function Login() {
  const navigate = useNavigate();
  const [form, setForm] = useState({ email: "", password: "" });
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState("");

  const update = (event) => {
    setForm((current) => ({ ...current, [event.target.name]: event.target.value }));
  };

  const submit = async (event) => {
    event.preventDefault();
    setError("");
    setLoading(true);
    try {
      const response = await api.post("/auth/login", form);
      const data = response.data;
      localStorage.setItem("inventra_token", data.token);
      localStorage.setItem(
        "inventra_user",
        JSON.stringify({ id: data.userId, name: data.name, email: data.email })
      );
      navigate("/", { replace: true });
    } catch (error) {
      console.error("Login failed", error);
      setError(errMsg(error) || "Invalid email or password.");
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="login-page">
      <section className="login-form-side">
        <div className="login-form-wrap">
          <Link className="login-brand" to="/login" aria-label="Inventra home">
            <span className="login-brand-mark">I</span>
            <span><strong>Inventra</strong><small>Inventory intelligence</small></span>
          </Link>

          <div className="login-heading">
            <span className="login-eyebrow">INVENTORY CONTROL CENTER</span>
            <h1>Welcome back</h1>
            <p>Sign in to manage stock, operations and AI-powered decisions.</p>
          </div>

          {error && <div className="login-error" role="alert">{error}</div>}

          <form className="login-form" onSubmit={submit}>
            <label>
              <span>Email address</span>
              <input
                name="email"
                type="email"
                value={form.email}
                onChange={update}
                placeholder="you@company.com"
                autoComplete="email"
                required
              />
            </label>

            <label>
              <span>Password</span>
              <input
                name="password"
                type="password"
                value={form.password}
                onChange={update}
                placeholder="Enter your password"
                autoComplete="current-password"
                required
              />
            </label>

            <button className="login-submit" type="submit" disabled={loading}>
              {loading ? "Signing in…" : "Sign in"}
              {!loading && <span aria-hidden="true">→</span>}
            </button>
          </form>

          <p className="login-footer">
            Don&apos;t have an account? <Link to="/register">Create an account</Link>
          </p>

          <div className="login-security">
            <span>✓</span> Secure JWT authentication · Your inventory data stays protected
          </div>
        </div>
      </section>

      <section className="login-visual" aria-hidden="true">
        <div className="login-glow login-glow-one" />
        <div className="login-glow login-glow-two" />
        <div className="login-grid" />

        <div className="login-visual-content">
          <span className="visual-kicker">INVENTRA INTELLIGENCE</span>
          <h2>Know what to stock.<br />Know when to act.</h2>
          <p>One command center for inventory visibility, demand forecasting and smarter replenishment.</p>

          <div className="login-insight-card">
            <div className="insight-top"><span>LIVE INVENTORY HEALTH</span><b><i /> Operational</b></div>
            <div className="insight-number">98.4%</div>
            <div className="insight-caption">Inventory visibility across your operation</div>
            <div className="insight-bars"><i /><i /><i /><i /><i /><i /><i /><i /><i /><i /></div>
          </div>

          <div className="login-points">
            <div><b>01</b><span>Real-time stock visibility</span></div>
            <div><b>02</b><span>AI demand forecasting</span></div>
            <div><b>03</b><span>Actionable replenishment</span></div>
          </div>
        </div>
      </section>
    </div>
  );
}
