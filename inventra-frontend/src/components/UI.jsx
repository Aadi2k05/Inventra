import React from 'react';
export function Page({eyebrow,title,description,actions,children}){return <><div className="page-head"><div><div className="eyebrow">{eyebrow}</div><h1>{title}</h1>{description&&<p>{description}</p>}</div>{actions&&<div className="head-actions">{actions}</div>}</div>{children}</>}
export function Button({children,variant='primary',...p}){return <button className={`btn ${variant}`} {...p}>{children}</button>}
export function Card({title,subtitle,actions,children,className=''}){return <section className={`card ${className}`}><div className="card-head">{title&&<div><h3>{title}</h3>{subtitle&&<p>{subtitle}</p>}</div>}{actions&&<div>{actions}</div>}</div>{children}</section>}
export function Metric({label,value,sub,trend}){return <div className="metric"><span>{label}</span><strong>{value}</strong>{sub&&<small className={trend==='bad'?'bad':''}>{sub}</small>}</div>}
export function Table({columns,rows,empty='No records found.'}){return <div className="table-wrap"><table><thead><tr>{columns.map(c=><th key={c.key}>{c.label}</th>)}</tr></thead><tbody>{rows.length?rows.map((r,i)=><tr key={r.id||i}>{columns.map(c=><td key={c.key}>{c.render?c.render(r):r[c.key]??'—'}</td>)}</tr>):<tr><td colSpan={columns.length} className="empty">{empty}</td></tr>}</tbody></table></div>}
export function Modal({title,onClose,children}){return <div className="modal-backdrop" onMouseDown={e=>e.target===e.currentTarget&&onClose()}><div className="modal"><div className="modal-head"><h3>{title}</h3><button onClick={onClose}>×</button></div>{children}</div></div>}
export function Field({label,...p}){return <label className="field"><span>{label}</span><input {...p}/></label>}
export function Select({label,children,...p}){return <label className="field"><span>{label}</span><select {...p}>{children}</select></label>}
export function Badge({children,type=''}){return <span className={`badge ${type}`}>{children}</span>}
export const fmt=n=>new Intl.NumberFormat().format(Number(n||0)); export const money=n=>new Intl.NumberFormat('en-IN',{style:'currency',currency:'INR',maximumFractionDigits:0}).format(Number(n||0));
export function Loader(){return <div className="loader"><span></span><span></span><span></span></div>}
export function ErrorBox({message}){return message?<div className="error-box">{message}</div>:null}
