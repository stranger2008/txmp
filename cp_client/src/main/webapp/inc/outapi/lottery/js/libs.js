(function(e) {
    String.prototype.trim === e && (String.prototype.trim = function() {
        return this.replace(/^\s+|\s+$/g, "")
    }),
    Array.prototype.reduce === e && (Array.prototype.reduce = function(t) {
        if (this === void 0 || this === null) throw new TypeError;
        var n = Object(this),
        r = n.length >>> 0,
        i = 0,
        s;
        if (typeof t != "function") throw new TypeError;
        if (r == 0 && arguments.length == 1) throw new TypeError;
        if (arguments.length >= 2) s = arguments[1];
        else do {
            if (i in n) {
                s = n[i++];
                break
            }
            if (++i >= r) throw new TypeError
        } while (! 0 );
        while (i < r) i in n && (s = t.call(e, s, n[i], i, n)),
        i++;
        return s
    })
})();
var Zepto = function() {
    function O(e) {
        return e == null ? String(e) : T[N.call(e)] || "object"
    }
    function M(e) {
        return O(e) == "function"
    }
    function _(e) {
        return e != null && e == e.window
    }
    function D(e) {
        return e != null && e.nodeType == e.DOCUMENT_NODE
    }
    function P(e) {
        return O(e) == "object"
    }
    function H(e) {
        return P(e) && !_(e) && e.__proto__ == Object.prototype
    }
    function B(e) {
        return e instanceof Array
    }
    function j(e) {
        return typeof e.length == "number"
    }
    function F(e) {
        return o.call(e,
        function(e) {
            return e != null
        })
    }
    function I(e) {
        return e.length > 0 ? n.fn.concat.apply([], e) : e
    }
    function q(e) {
        return e.replace(/::/g, "/").replace(/([A-Z]+)([A-Z][a-z])/g, "$1_$2").replace(/([a-z\d])([A-Z])/g, "$1_$2").replace(/_/g, "-").toLowerCase()
    }
    function R(e) {
        return e in f ? f[e] : f[e] = new RegExp("(^|\\s)" + e + "(\\s|$)")
    }
    function U(e, t) {
        return typeof t == "number" && !c[q(e)] ? t + "px": t
    }
    function z(e) {
        var t, n;
        return a[e] || (t = u.createElement(e), u.body.appendChild(t), n = l(t, "").getPropertyValue("display"), t.parentNode.removeChild(t), n == "none" && (n = "block"), a[e] = n),
        a[e]
    }
    function W(e) {
        return "children" in e ? s.call(e.children) : n.map(e.childNodes,
        function(e) {
            if (e.nodeType == 1) return e
        })
    }
    function X(n, r, i) {
        for (t in r) i && (H(r[t]) || B(r[t])) ? (H(r[t]) && !H(n[t]) && (n[t] = {}), B(r[t]) && !B(n[t]) && (n[t] = []), X(n[t], r[t], i)) : r[t] !== e && (n[t] = r[t])
    }
    function V(t, r) {
        return r === e ? n(t) : n(t).filter(r)
    }
    function $(e, t, n, r) {
        return M(t) ? t.call(e, n, r) : t
    }
    function J(e, t, n) {
        n == null ? e.removeAttribute(t) : e.setAttribute(t, n)
    }
    function K(t, n) {
        var r = t.className,
        i = r && r.baseVal !== e;
        if (n === e) return i ? r.baseVal: r;
        i ? r.baseVal = n: t.className = n
    }
    function Q(e) {
        var t;
        try {
            return e ? e == "true" || (e == "false" ? !1 : e == "null" ? null: isNaN(t = Number(e)) ? /^[\[\{]/.test(e) ? n.parseJSON(e) : e: t) : e
        } catch(r) {
            return e
        }
    }
    function G(e, t) {
        t(e);
        for (var n in e.childNodes) G(e.childNodes[n], t)
    }
    var e, t, n, r, i = [],
    s = i.slice,
    o = i.filter,
    u = window.document,
    a = {},
    f = {},
    l = u.defaultView.getComputedStyle,
    c = {
        "column-count": 1,
        columns: 1,
        "font-weight": 1,
        "line-height": 1,
        opacity: 1,
        "z-index": 1,
        zoom: 1
    },
    h = /^\s*<(\w+|!)[^>]*>/,
    p = /<(?!area|br|col|embed|hr|img|input|link|meta|param)(([\w:]+)[^>]*)\/>/ig,
    d = /^(?:body|html)$/i,
    v = ["val", "css", "html", "text", "data", "width", "height", "offset"],
    m = ["after", "prepend", "before", "append"],
    g = u.createElement("table"),
    y = u.createElement("tr"),
    b = {
        tr: u.createElement("tbody"),
        tbody: g,
        thead: g,
        tfoot: g,
        td: y,
        th: y,
        "*": u.createElement("div")
    },
    w = /complete|loaded|interactive/,
    E = /^\.([\w-]+)$/,
    S = /^#([\w-]*)$/,
    x = /^[\w-]+$/,
    T = {},
    N = T.toString,
    C = {},
    k,
    L,
    A = u.createElement("div");
    return C.matches = function(e, t) {
        if (!e || e.nodeType !== 1) return ! 1;
        var n = e.webkitMatchesSelector || e.mozMatchesSelector || e.oMatchesSelector || e.matchesSelector;
        if (n) return n.call(e, t);
        var r, i = e.parentNode,
        s = !i;
        return s && (i = A).appendChild(e),
        r = ~C.qsa(i, t).indexOf(e),
        s && A.removeChild(e),
        r
    },
    k = function(e) {
        return e.replace(/-+(.)?/g,
        function(e, t) {
            return t ? t.toUpperCase() : ""
        })
    },
    L = function(e) {
        return o.call(e,
        function(t, n) {
            return e.indexOf(t) == n
        })
    },
    C.fragment = function(t, r, i) {
        t.replace && (t = t.replace(p, "<$1></$2>")),
        r === e && (r = h.test(t) && RegExp.$1),
        r in b || (r = "*");
        var o, u, a = b[r];
        return a.innerHTML = "" + t,
        u = n.each(s.call(a.childNodes),
        function() {
            a.removeChild(this)
        }),
        H(i) && (o = n(u), n.each(i,
        function(e, t) {
            v.indexOf(e) > -1 ? o[e](t) : o.attr(e, t)
        })),
        u
    },
    C.Z = function(e, t) {
        return e = e || [],
        e.__proto__ = n.fn,
        e.selector = t || "",
        e
    },
    C.isZ = function(e) {
        return e instanceof C.Z
    },
    C.init = function(t, r) {
        if (!t) return C.Z();
        if (M(t)) return n(u).ready(t);
        if (C.isZ(t)) return t;
        var i;
        if (B(t)) i = F(t);
        else if (P(t)) i = [H(t) ? n.extend({},
        t) : t],
        t = null;
        else if (h.test(t)) i = C.fragment(t.trim(), RegExp.$1, r),
        t = null;
        else {
            if (r !== e) return n(r).find(t);
            i = C.qsa(u, t)
        }
        return C.Z(i, t)
    },
    n = function(e, t) {
        return C.init(e, t)
    },
    n.extend = function(e) {
        var t, n = s.call(arguments, 1);
        return typeof e == "boolean" && (t = e, e = n.shift()),
        n.forEach(function(n) {
            X(e, n, t)
        }),
        e
    },
    C.qsa = function(e, t) {
        var n;
        return D(e) && S.test(t) ? (n = e.getElementById(RegExp.$1)) ? [n] : [] : e.nodeType !== 1 && e.nodeType !== 9 ? [] : s.call(E.test(t) ? e.getElementsByClassName(RegExp.$1) : x.test(t) ? e.getElementsByTagName(t) : e.querySelectorAll(t))
    },
    n.contains = function(e, t) {
        return e !== t && e.contains(t)
    },
    n.type = O,
    n.isFunction = M,
    n.isWindow = _,
    n.isArray = B,
    n.isPlainObject = H,
    n.isEmptyObject = function(e) {
        var t;
        for (t in e) return ! 1;
        return ! 0
    },
    n.inArray = function(e, t, n) {
        return i.indexOf.call(t, e, n)
    },
    n.camelCase = k,
    n.uuid = 0,
    n.trim=function(e){return e.trim()},
    n.support = {},
    n.expr = {},
    n.map = function(e, t) {
        var n, r = [],
        i,
        s;
        if (j(e)) for (i = 0; i < e.length; i++) n = t(e[i], i),
        n != null && r.push(n);
        else for (s in e) n = t(e[s], s),
        n != null && r.push(n);
        return I(r)
    },
    n.each = function(e, t) {
        var n, r;
        if (j(e)) {
            for (n = 0; n < e.length; n++) if (t.call(e[n], n, e[n]) === !1) return e
        } else for (r in e) if (t.call(e[r], r, e[r]) === !1) return e;
        return e
    },
    n.grep = function(e, t) {
        return o.call(e, t)
    },
    window.JSON && (n.parseJSON = JSON.parse),
    n.each("Boolean Number String Function Array Date RegExp Object Error".split(" "),
    function(e, t) {
        T["[object " + t + "]"] = t.toLowerCase()
    }),
    n.fn = {
        forEach: i.forEach,
        reduce: i.reduce,
        push: i.push,
        sort: i.sort,
        indexOf: i.indexOf,
        concat: i.concat,
        map: function(e) {
            return n(n.map(this,
            function(t, n) {
                return e.call(t, n, t)
            }))
        },
        slice: function() {
            return n(s.apply(this, arguments))
        },
        ready: function(e) {
            return w.test(u.readyState) ? e(n) : u.addEventListener("DOMContentLoaded",
            function() {
                e(n)
            },
            !1),
            this
        },
        get: function(t) {
            return t === e ? s.call(this) : this[t >= 0 ? t: t + this.length]
        },
        toArray: function() {
            return this.get()
        },
        size: function() {
            return this.length
        },
        remove: function() {
            return this.each(function() {
                this.parentNode != null && this.parentNode.removeChild(this)
            })
        },
        each: function(e) {
            return i.every.call(this,
            function(t, n) {
                return e.call(t, n, t) !== !1
            }),
            this
        },
        filter: function(e) {
            return M(e) ? this.not(this.not(e)) : n(o.call(this,
            function(t) {
                return C.matches(t, e)
            }))
        },
        add: function(e, t) {
            return n(L(this.concat(n(e, t))))
        },
        is: function(e) {
            return this.length > 0 && C.matches(this[0], e)
        },
        not: function(t) {
            var r = [];
            if (M(t) && t.call !== e) this.each(function(e) {
                t.call(this, e) || r.push(this)
            });
            else {
                var i = typeof t == "string" ? this.filter(t) : j(t) && M(t.item) ? s.call(t) : n(t);
                this.forEach(function(e) {
                    i.indexOf(e) < 0 && r.push(e)
                })
            }
            return n(r)
        },
        has: function(e) {
            return this.filter(function() {
                return P(e) ? n.contains(this, e) : n(this).find(e).size()
            })
        },
        eq: function(e) {
            return e === -1 ? this.slice(e) : this.slice(e, +e + 1)
        },
        first: function() {
            var e = this[0];
            return e && !P(e) ? e: n(e)
        },
        last: function() {
            var e = this[this.length - 1];
            return e && !P(e) ? e: n(e)
        },
        find: function(e) {
            var t, r = this;
            return typeof e == "object" ? t = n(e).filter(function() {
                var e = this;
                return i.some.call(r,
                function(t) {
                    return n.contains(t, e)
                })
            }) : this.length == 1 ? t = n(C.qsa(this[0], e)) : t = this.map(function() {
                return C.qsa(this, e)
            }),
            t
        },
        closest: function(e, t) {
            var r = this[0],
            i = !1;
            typeof e == "object" && (i = n(e));
            while (r && !(i ? i.indexOf(r) >= 0 : C.matches(r, e))) r = r !== t && !D(r) && r.parentNode;
            return n(r)
        },
        parents: function(e) {
            var t = [],
            r = this;
            while (r.length > 0) r = n.map(r,
            function(e) {
                if ((e = e.parentNode) && !D(e) && t.indexOf(e) < 0) return t.push(e),
                e
            });
            return V(t, e)
        },
        parent: function(e) {
            return V(L(this.pluck("parentNode")), e)
        },
        children: function(e) {
            return V(this.map(function() {
                return W(this)
            }), e)
        },
        contents: function() {
            return this.map(function() {
                return s.call(this.childNodes)
            })
        },
        siblings: function(e) {
            return V(this.map(function(e, t) {
                return o.call(W(t.parentNode),
                function(e) {
                    return e !== t
                })
            }), e)
        },
        empty: function() {
            return this.each(function() {
                this.innerHTML = ""
            })
        },
        pluck: function(e) {
            return n.map(this,
            function(t) {
                return t[e]
            })
        },
        show: function() {
            return this.each(function() {
                this.style.display == "none" && (this.style.display = null),
                l(this, "").getPropertyValue("display") == "none" && (this.style.display = z(this.nodeName))
            })
        },
        replaceWith: function(e) {
            return this.before(e).remove()
        },
        wrap: function(e) {
            var t = M(e);
            if (this[0] && !t) var r = n(e).get(0),
            i = r.parentNode || this.length > 1;
            return this.each(function(s) {
                n(this).wrapAll(t ? e.call(this, s) : i ? r.cloneNode(!0) : r)
            })
        },
        wrapAll: function(e) {
            if (this[0]) {
                n(this[0]).before(e = n(e));
                var t;
                while ((t = e.children()).length) e = t.first();
                n(e).append(this)
            }
            return this
        },
        wrapInner: function(e) {
            var t = M(e);
            return this.each(function(r) {
                var i = n(this),
                s = i.contents(),
                o = t ? e.call(this, r) : e;
                s.length ? s.wrapAll(o) : i.append(o)
            })
        },
        unwrap: function() {
            return this.parent().each(function() {
                n(this).replaceWith(n(this).children())
            }),
            this
        },
        clone: function() {
            return this.map(function() {
                return this.cloneNode(!0)
            })
        },
        hide: function() {
            return this.css("display", "none")
        },
        toggle: function(t) {
            return this.each(function() {
                var r = n(this); (t === e ? r.css("display") == "none": t) ? r.show() : r.hide()
            })
        },
        prev: function(e) {
            return n(this.pluck("previousElementSibling")).filter(e || "*")
        },
        next: function(e) {
            return n(this.pluck("nextElementSibling")).filter(e || "*")
        },
        html: function(t) {
            return t === e ? this.length > 0 ? this[0].innerHTML: null: this.each(function(e) {
                var r = this.innerHTML;
                n(this).empty().append($(this, t, e, r))
            })
        },
        text: function(t) {
            return t === e ? this.length > 0 ? this[0].textContent: null: this.each(function() {
                this.textContent = t
            })
        },
        attr: function(n, r) {
            var i;
            return typeof n == "string" && r === e ? this.length == 0 || this[0].nodeType !== 1 ? e: n == "value" && this[0].nodeName == "INPUT" ? this.val() : !(i = this[0].getAttribute(n)) && n in this[0] ? this[0][n] : i: this.each(function(e) {
                if (this.nodeType !== 1) return;
                if (P(n)) for (t in n) J(this, t, n[t]);
                else J(this, n, $(this, r, e, this.getAttribute(n)))
            })
        },
        removeAttr: function(e) {
            return this.each(function() {
                this.nodeType === 1 && J(this, e)
            })
        },
        prop: function(t, n) {
            return n === e ? this[0] && this[0][t] : this.each(function(e) {
                this[t] = $(this, n, e, this[t])
            })
        },
        data: function(t, n) {
            var r = this.attr("data-" + q(t), n);
            return r !== null ? Q(r) : e
        },
        val: function(t) {
            return t === e ? this[0] && (this[0].multiple ? n(this[0]).find("option").filter(function(e) {
                return this.selected
            }).pluck("value") : this[0].value) : this.each(function(e) {
                this.value = $(this, t, e, this.value)
            })
        },
        offset: function(e) {
            if (e) return this.each(function(t) {
                var r = n(this),
                i = $(this, e, t, r.offset()),
                s = r.offsetParent().offset(),
                o = {
                    top: i.top - s.top,
                    left: i.left - s.left
                };
                r.css("position") == "static" && (o.position = "relative"),
                r.css(o)
            });
            if (this.length == 0) return null;
            var t = this[0].getBoundingClientRect();
            return {
                left: t.left + window.pageXOffset,
                top: t.top + window.pageYOffset,
                width: Math.round(t.width),
                height: Math.round(t.height)
            }
        },
        css: function(e, n) {
            if (arguments.length < 2 && typeof e == "string") return this[0] && (this[0].style[k(e)] || l(this[0], "").getPropertyValue(e));
            var r = "";
            if (O(e) == "string") ! n && n !== 0 ? this.each(function() {
                this.style.removeProperty(q(e))
            }) : r = q(e) + ":" + U(e, n);
            else for (t in e) ! e[t] && e[t] !== 0 ? this.each(function() {
                this.style.removeProperty(q(t))
            }) : r += q(t) + ":" + U(t, e[t]) + ";";
            return this.each(function() {
                this.style.cssText += ";" + r
            })
        },
        index: function(e) {
            return e ? this.indexOf(n(e)[0]) : this.parent().children().indexOf(this[0])
        },
        hasClass: function(e) {
            return i.some.call(this,
            function(e) {
                return this.test(K(e))
            },
            R(e))
        },
        addClass: function(e) {
            return this.each(function(t) {
                r = [];
                var i = K(this),
                s = $(this, e, t, i);
                s.split(/\s+/g).forEach(function(e) {
                    n(this).hasClass(e) || r.push(e)
                },
                this),
                r.length && K(this, i + (i ? " ": "") + r.join(" "))
            })
        },
        removeClass: function(t) {
            return this.each(function(n) {
                if (t === e) return K(this, "");
                r = K(this),
                $(this, t, n, r).split(/\s+/g).forEach(function(e) {
                    r = r.replace(R(e), " ")
                }),
                K(this, r.trim())
            })
        },
        toggleClass: function(t, r) {
            return this.each(function(i) {
                var s = n(this),
                o = $(this, t, i, K(this));
                o.split(/\s+/g).forEach(function(t) { (r === e ? !s.hasClass(t) : r) ? s.addClass(t) : s.removeClass(t)
                })
            })
        },
        scrollTop: function() {
            if (!this.length) return;
            return "scrollTop" in this[0] ? this[0].scrollTop: this[0].scrollY
        },
        position: function() {
            if (!this.length) return;
            var e = this[0],
            t = this.offsetParent(),
            r = this.offset(),
            i = d.test(t[0].nodeName) ? {
                top: 0,
                left: 0
            }: t.offset();
            return r.top -= parseFloat(n(e).css("margin-top")) || 0,
            r.left -= parseFloat(n(e).css("margin-left")) || 0,
            i.top += parseFloat(n(t[0]).css("border-top-width")) || 0,
            i.left += parseFloat(n(t[0]).css("border-left-width")) || 0,
            {
                top: r.top - i.top,
                left: r.left - i.left
            }
        },
        offsetParent: function() {
            return this.map(function() {
                var e = this.offsetParent || u.body;
                while (e && !d.test(e.nodeName) && n(e).css("position") == "static") e = e.offsetParent;
                return e
            })
        }
    },
    n.fn.detach = n.fn.remove,
    ["width", "height"].forEach(function(t) {
        n.fn[t] = function(r) {
            var i, s = this[0],
            o = t.replace(/./,
            function(e) {
                return e[0].toUpperCase()
            });
            return r === e ? _(s) ? s["inner" + o] : D(s) ? s.documentElement["offset" + o] : (i = this.offset()) && i[t] : this.each(function(e) {
                s = n(this),
                s.css(t, $(this, r, e, s[t]()))
            })
        }
    }),
    m.forEach(function(e, t) {
        var r = t % 2;
        n.fn[e] = function() {
            var e, i = n.map(arguments,
            function(t) {
                return e = O(t),
                e == "object" || e == "array" || t == null ? t: C.fragment(t)
            }),
            s,
            o = this.length > 1;
            return i.length < 1 ? this: this.each(function(e, u) {
                s = r ? u: u.parentNode,
                u = t == 0 ? u.nextSibling: t == 1 ? u.firstChild: t == 2 ? u: null,
                i.forEach(function(e) {
                    if (o) e = e.cloneNode(!0);
                    else if (!s) return n(e).remove();
                    G(s.insertBefore(e, u),
                    function(e) {
                        e.nodeName != null && e.nodeName.toUpperCase() === "SCRIPT" && (!e.type || e.type === "text/javascript") && !e.src && window.eval.call(window, e.innerHTML)
                    })
                })
            })
        },
        n.fn[r ? e + "To": "insert" + (t ? "Before": "After")] = function(t) {
            return n(t)[e](this),
            this
        }
    }),
    C.Z.prototype = n.fn,
    C.uniq = L,
    C.deserializeValue = Q,
    n.zepto = C,
    n
} ();
window.Zepto = Zepto,
"$" in window || (window.$ = Zepto),
function(e) {
    function t(e) {
        var t = this.os = {},
        n = this.browser = {},
        r = e.match(/WebKit\/([\d.]+)/),
        i = e.match(/(Android)\s+([\d.]+)/),
        s = e.match(/(iPad).*OS\s([\d_]+)/),
        o = !s && e.match(/(iPhone\sOS)\s([\d_]+)/),
        u = e.match(/(webOS|hpwOS)[\s\/]([\d.]+)/),
        a = u && e.match(/TouchPad/),
        f = e.match(/Kindle\/([\d.]+)/),
        l = e.match(/Silk\/([\d._]+)/),
        c = e.match(/(BlackBerry).*Version\/([\d.]+)/),
        h = e.match(/(BB10).*Version\/([\d.]+)/),
        p = e.match(/(RIM\sTablet\sOS)\s([\d.]+)/),
        d = e.match(/PlayBook/),
        v = e.match(/Chrome\/([\d.]+)/) || e.match(/CriOS\/([\d.]+)/),
        m = e.match(/Firefox\/([\d.]+)/);
        if (n.webkit = !!r) n.version = r[1];
        i && (t.android = !0, t.version = i[2]),
        o && (t.ios = t.iphone = !0, t.version = o[2].replace(/_/g, ".")),
        s && (t.ios = t.ipad = !0, t.version = s[2].replace(/_/g, ".")),
        u && (t.webos = !0, t.version = u[2]),
        a && (t.touchpad = !0),
        c && (t.blackberry = !0, t.version = c[2]),
        h && (t.bb10 = !0, t.version = h[2]),
        p && (t.rimtabletos = !0, t.version = p[2]),
        d && (n.playbook = !0),
        f && (t.kindle = !0, t.version = f[1]),
        l && (n.silk = !0, n.version = l[1]),
        !l && t.android && e.match(/Kindle Fire/) && (n.silk = !0),
        v && (n.chrome = !0, n.version = v[1]),
        m && (n.firefox = !0, n.version = m[1]),
        t.tablet = !!(s || d || i && !e.match(/Mobile/) || m && e.match(/Tablet/)),
        t.phone = !t.tablet && !!(i || o || u || c || h || v && e.match(/Android/) || v && e.match(/CriOS\/([\d.]+)/) || m && e.match(/Mobile/))
    }
    t.call(e, navigator.userAgent),
    e.__detect = t
} (Zepto),
function(e) {
    function o(e) {
        return e._zid || (e._zid = r++)
    }
    function u(e, t, r, i) {
        t = a(t);
        if (t.ns) var s = f(t.ns);
        return (n[o(e)] || []).filter(function(e) {
            return e && (!t.e || e.e == t.e) && (!t.ns || s.test(e.ns)) && (!r || o(e.fn) === o(r)) && (!i || e.sel == i)
        })
    }
    function a(e) {
        var t = ("" + e).split(".");
        return {
            e: t[0],
            ns: t.slice(1).sort().join(" ")
        }
    }
    function f(e) {
        return new RegExp("(?:^| )" + e.replace(" ", " .* ?") + "(?: |$)")
    }
    function l(t, n, r) {
        e.type(t) != "string" ? e.each(t, r) : t.split(/\s/).forEach(function(e) {
            r(e, n)
        })
    }
    function c(e, t) {
        return e.del && (e.e == "focus" || e.e == "blur") || !!t
    }
    function h(e) {
        return s[e] || e
    }
    function p(t, r, i, u, f, p) {
        var d = o(t),
        v = n[d] || (n[d] = []);
        l(r, i,
        function(n, r) {
            var i = a(n);
            i.fn = r,
            i.sel = u,
            i.e in s && (r = function(t) {
                var n = t.relatedTarget;
                if (!n || n !== this && !e.contains(this, n)) return i.fn.apply(this, arguments)
            }),
            i.del = f && f(r, n);
            var o = i.del || r;
            i.proxy = function(e) {
                var n = o.apply(t, [e].concat(e.data));
                return n === !1 && (e.preventDefault(), e.stopPropagation()),
                n
            },
            i.i = v.length,
            v.push(i),
            t.addEventListener(h(i.e), i.proxy, c(i, p))
        })
    }
    function d(e, t, r, i, s) {
        var a = o(e);
        l(t || "", r,
        function(t, r) {
            u(e, t, r, i).forEach(function(t) {
                delete n[a][t.i],
                e.removeEventListener(h(t.e), t.proxy, c(t, s))
            })
        })
    }
    function b(t) {
        var n, r = {
            originalEvent: t
        };
        for (n in t) ! g.test(n) && t[n] !== undefined && (r[n] = t[n]);
        return e.each(y,
        function(e, n) {
            r[e] = function() {
                return this[n] = v,
                t[e].apply(t, arguments)
            },
            r[n] = m
        }),
        r
    }
    function w(e) {
        if (! ("defaultPrevented" in e)) {
            e.defaultPrevented = !1;
            var t = e.preventDefault;
            e.preventDefault = function() {
                this.defaultPrevented = !0,
                t.call(this)
            }
        }
    }
    var t = e.zepto.qsa,
    n = {},
    r = 1,
    i = {},
    s = {
        mouseenter: "mouseover",
        mouseleave: "mouseout"
    };
    i.click = i.mousedown = i.mouseup = i.mousemove = "MouseEvents",
    e.event = {
        add: p,
        remove: d
    },
    e.proxy = function(t, n) {
        if (e.isFunction(t)) {
            var r = function() {
                return t.apply(n, arguments)
            };
            return r._zid = o(t),
            r
        }
        if (typeof n == "string") return e.proxy(t[n], t);
        throw new TypeError("expected function")
    },
    e.fn.bind = function(e, t) {
        return this.each(function() {
            p(this, e, t)
        })
    },
    e.fn.unbind = function(e, t) {
        return this.each(function() {
            d(this, e, t)
        })
    },
    e.fn.one = function(e, t) {
        return this.each(function(n, r) {
            p(this, e, t, null,
            function(e, t) {
                return function() {
                    var n = e.apply(r, arguments);
                    return d(r, t, e),
                    n
                }
            })
        })
    };
    var v = function() {
        return ! 0
    },
    m = function() {
        return ! 1
    },
    g = /^([A-Z]|layer[XY]$)/,
    y = {
        preventDefault: "isDefaultPrevented",
        stopImmediatePropagation: "isImmediatePropagationStopped",
        stopPropagation: "isPropagationStopped"
    };
    e.fn.delegate = function(t, n, r) {
        return this.each(function(i, s) {
            p(s, n, r, t,
            function(n) {
                return function(r) {
                    var i, o = e(r.target).closest(t, s).get(0);
                    if (o) return i = e.extend(b(r), {
                        currentTarget: o,
                        liveFired: s
                    }),
                    n.apply(o, [i].concat([].slice.call(arguments, 1)))
                }
            })
        })
    },
    e.fn.undelegate = function(e, t, n) {
        return this.each(function() {
            d(this, t, n, e)
        })
    },
    e.fn.live = function(t, n) {
        return e(document.body).delegate(this.selector, t, n),
        this
    },
    e.fn.die = function(t, n) {
        return e(document.body).undelegate(this.selector, t, n),
        this
    },
    e.fn.on = function(t, n, r) {
        return ! n || e.isFunction(n) ? this.bind(t, n || r) : this.delegate(n, t, r)
    },
    e.fn.off = function(t, n, r) {
        return ! n || e.isFunction(n) ? this.unbind(t, n || r) : this.undelegate(n, t, r)
    },
    e.fn.trigger = function(t, n) {
        if (typeof t == "string" || e.isPlainObject(t)) t = e.Event(t);
        return w(t),
        t.data = n,
        this.each(function() {
            "dispatchEvent" in this && this.dispatchEvent(t)
        })
    },
    e.fn.triggerHandler = function(t, n) {
        var r, i;
        return this.each(function(s, o) {
            r = b(typeof t == "string" ? e.Event(t) : t),
            r.data = n,
            r.target = o,
            e.each(u(o, t.type || t),
            function(e, t) {
                i = t.proxy(r);
                if (r.isImmediatePropagationStopped()) return ! 1
            })
        }),
        i
    },
    "focusin focusout load resize scroll unload click dblclick mousedown mouseup mousemove mouseover mouseout mouseenter mouseleave change select keydown keypress keyup error".split(" ").forEach(function(t) {
        e.fn[t] = function(e) {
            return e ? this.bind(t, e) : this.trigger(t)
        }
    }),
    ["focus", "blur"].forEach(function(t) {
        e.fn[t] = function(e) {
            return e ? this.bind(t, e) : this.each(function() {
                try {
                    this[t]()
                } catch(e) {}
            }),
            this
        }
    }),
    e.Event = function(e, t) {
        typeof e != "string" && (t = e, e = t.type);
        var n = document.createEvent(i[e] || "Events"),
        r = !0;
        if (t) for (var s in t) s == "bubbles" ? r = !!t[s] : n[s] = t[s];
        return n.initEvent(e, r, !0, null, null, null, null, null, null, null, null, null, null, null, null),
        n.isDefaultPrevented = function() {
            return this.defaultPrevented
        },
        n
    }
} (Zepto),
function($) {
    function triggerAndReturn(e, t, n) {
        var r = $.Event(t);
        return $(e).trigger(r, n),
        !r.defaultPrevented
    }
    function triggerGlobal(e, t, n, r) {
        if (e.global) return triggerAndReturn(t || document, n, r)
    }
    function ajaxStart(e) {
        e.global && $.active++===0 && triggerGlobal(e, null, "ajaxStart")
    }
    function ajaxStop(e) {
        e.global && !--$.active && triggerGlobal(e, null, "ajaxStop")
    }
    function ajaxBeforeSend(e, t) {
        var n = t.context;
        if (t.beforeSend.call(n, e, t) === !1 || triggerGlobal(t, n, "ajaxBeforeSend", [e, t]) === !1) return ! 1;
        triggerGlobal(t, n, "ajaxSend", [e, t])
    }
    function ajaxSuccess(e, t, n) {
        var r = n.context,
        i = "success";
        n.success.call(r, e, i, t),
        triggerGlobal(n, r, "ajaxSuccess", [t, n, e]),
        ajaxComplete(i, t, n)
    }
    function ajaxError(e, t, n, r) {
        var i = r.context;
        r.error.call(i, n, t, e),
        triggerGlobal(r, i, "ajaxError", [n, r, e]),
        ajaxComplete(t, n, r)
    }
    function ajaxComplete(e, t, n) {
        var r = n.context;
        n.complete.call(r, t, e),
        triggerGlobal(n, r, "ajaxComplete", [t, n]),
        ajaxStop(n)
    }
    function empty() {}
    function mimeToDataType(e) {
        return e && (e = e.split(";", 2)[0]),
        e && (e == htmlType ? "html": e == jsonType ? "json": scriptTypeRE.test(e) ? "script": xmlTypeRE.test(e) && "xml") || "text"
    }
    function appendQuery(e, t) {
        return (e + "&" + t).replace(/[&?]{1,2}/, "?")
    }
    function serializeData(e) {
        e.processData && e.data && $.type(e.data) != "string" && (e.data = $.param(e.data, e.traditional)),
        e.data && (!e.type || e.type.toUpperCase() == "GET") && (e.url = appendQuery(e.url, e.data))
    }
    function parseArguments(e, t, n, r) {
        var i = !$.isFunction(t);
        return {
            url: e,
            data: i ? t: undefined,
            success: i ? $.isFunction(n) ? n: undefined: t,
            dataType: i ? r || n: n
        }
    }
    function serialize(e, t, n, r) {
        var i, s = $.isArray(t);
        $.each(t,
        function(t, o) {
            i = $.type(o),
            r && (t = n ? r: r + "[" + (s ? "": t) + "]"),
            !r && s ? e.add(o.name, o.value) : i == "array" || !n && i == "object" ? serialize(e, o, n, t) : e.add(t, o)
        })
    }
    var jsonpID = 0,
    document = window.document,
    key, name, rscript = /<script\b[^<]*(?:(?!<\/script>)<[^<]*)*<\/script>/gi,
    scriptTypeRE = /^(?:text|application)\/javascript/i,
    xmlTypeRE = /^(?:text|application)\/xml/i,
    jsonType = "application/json",
    htmlType = "text/html",
    blankRE = /^\s*$/;
    $.active = 0,
    $.ajaxJSONP = function(e) {
        if ("type" in e) {
            var t = "jsonp" + ++jsonpID,
            n = document.createElement("script"),
            r = function() {
                clearTimeout(o),
                $(n).remove(),
                delete window[t]
            },
            i = function(n) {
                r();
                if (!n || n == "timeout") window[t] = empty;
                ajaxError(null, n || "abort", s, e)
            },
            s = {
                abort: i
            },
            o;
            return ajaxBeforeSend(s, e) === !1 ? (i("abort"), !1) : (window[t] = function(t) {
                r(),
                ajaxSuccess(t, s, e)
            },
            n.onerror = function() {
                i("error")
            },
            n.src = e.url.replace(/=\?/, "=" + t), $("head").append(n), e.timeout > 0 && (o = setTimeout(function() {
                i("timeout")
            },
            e.timeout)), s)
        }
        return $.ajax(e)
    },
    $.ajaxSettings = {
        type: "GET",
        beforeSend: empty,
        success: empty,
        error: empty,
        complete: empty,
        context: null,
        global: !0,
        xhr: function() {
            return new window.XMLHttpRequest
        },
        accepts: {
            script: "text/javascript, application/javascript",
            json: jsonType,
            xml: "application/xml, text/xml",
            html: htmlType,
            text: "text/plain"
        },
        crossDomain: !1,
        timeout: 0,
        processData: !0,
        cache: !0
    },
    $.ajax = function(options) {
        var settings = $.extend({},
        options || {});
        for (key in $.ajaxSettings) settings[key] === undefined && (settings[key] = $.ajaxSettings[key]);
        ajaxStart(settings),
        settings.crossDomain || (settings.crossDomain = /^([\w-]+:)?\/\/([^\/]+)/.test(settings.url) && RegExp.$2 != window.location.host),
        settings.url || (settings.url = window.location.toString()),
        serializeData(settings),
        settings.cache === !1 && (settings.url = appendQuery(settings.url, "_=" + Date.now()));
        var dataType = settings.dataType,
        hasPlaceholder = /=\?/.test(settings.url);
        if (dataType == "jsonp" || hasPlaceholder) return hasPlaceholder || (settings.url = appendQuery(settings.url, "callback=?")),
        $.ajaxJSONP(settings);
        var mime = settings.accepts[dataType],
        baseHeaders = {},
        protocol = /^([\w-]+:)\/\//.test(settings.url) ? RegExp.$1: window.location.protocol,
        xhr = settings.xhr(),
        abortTimeout;
        settings.crossDomain || (baseHeaders["X-Requested-With"] = "XMLHttpRequest"),
        mime && (baseHeaders.Accept = mime, mime.indexOf(",") > -1 && (mime = mime.split(",", 2)[0]), xhr.overrideMimeType && xhr.overrideMimeType(mime));
        if (settings.contentType || settings.contentType !== !1 && settings.data && settings.type.toUpperCase() != "GET") baseHeaders["Content-Type"] = settings.contentType || "application/x-www-form-urlencoded";
        settings.headers = $.extend(baseHeaders, settings.headers || {}),
        xhr.onreadystatechange = function() {
            if (xhr.readyState == 4) {
                xhr.onreadystatechange = empty,
                clearTimeout(abortTimeout);
                var result, error = !1;
                if (xhr.status >= 200 && xhr.status < 300 || xhr.status == 304 || xhr.status == 0 && protocol == "file:") {
                    dataType = dataType || mimeToDataType(xhr.getResponseHeader("content-type")),
                    result = xhr.responseText;
                    try {
                        dataType == "script" ? (1, eval)(result) : dataType == "xml" ? result = xhr.responseXML: dataType == "json" && (result = blankRE.test(result) ? null: $.parseJSON(result))
                    } catch(e) {
                        error = e
                    }
                    error ? ajaxError(error, "parsererror", xhr, settings) : ajaxSuccess(result, xhr, settings)
                } else ajaxError(null, xhr.status ? "error": "abort", xhr, settings)
            }
        };
        var async = "async" in settings ? settings.async: !0;
        xhr.open(settings.type, settings.url, async);
        for (name in settings.headers) xhr.setRequestHeader(name, settings.headers[name]);
        return null;
    },
    $.get = function(e, t, n, r) {
        return $.ajax(parseArguments.apply(null, arguments))
    },
    $.post = function(e, t, n, r) {
        var i = parseArguments.apply(null, arguments);
        return i.type = "POST",
        $.ajax(i)
    },
    $.getJSON = function(e, t, n) {
        var r = parseArguments.apply(null, arguments);
        return r.dataType = "json",
        $.ajax(r)
    },
    $.fn.load = function(e, t, n) {
        if (!this.length) return this;
        var r = this,
        i = e.split(/\s/),
        s,
        o = parseArguments(e, t, n),
        u = o.success;
        return i.length > 1 && (o.url = i[0], s = i[1]),
        o.success = function(e) {
            r.html(s ? $("<div>").html(e.replace(rscript, "")).find(s) : e),
            u && u.apply(r, arguments)
        },
        $.ajax(o),
        this
    };
    var escape = encodeURIComponent;
    $.param = function(e, t) {
        var n = [];
        return n.add = function(e, t) {
            this.push(escape(e) + "=" + escape(t))
        },
        serialize(n, e, t),
        n.join("&").replace(/%20/g, "+")
    }
} (Zepto),
function(e) {
    e.fn.serializeArray = function() {
        var t = [],
        n;
        return e(Array.prototype.slice.call(this.get(0).elements)).each(function() {
            n = e(this);
            var r = n.attr("type");
            this.nodeName.toLowerCase() != "fieldset" && !this.disabled && r != "submit" && r != "reset" && r != "button" && (r != "radio" && r != "checkbox" || this.checked) && t.push({
                name: n.attr("name"),
                value: n.val()
            })
        }),
        t
    },
    e.fn.serialize = function() {
        var e = [];
        return this.serializeArray().forEach(function(t) {
            e.push(encodeURIComponent(t.name) + "=" + encodeURIComponent(t.value))
        }),
        e.join("&")
    },
    e.fn.submit = function(t) {
        if (t) this.bind("submit", t);
        else if (this.length) {
            var n = e.Event("submit");
            this.eq(0).trigger(n),
            n.defaultPrevented || this.get(0).submit()
        }
        return this
    }
} (Zepto),
function(e, t) {
    function y(e) {
        return b(e.replace(/([a-z])([A-Z])/, "$1-$2"))
    }
    function b(e) {
        return e.toLowerCase()
    }
    function w(e) {
        return r ? r + e: b(e)
    }
    var n = "",
    r, i, s, o = {
        Webkit: "webkit",
        Moz: "",
        O: "o",
        ms: "MS"
    },
    u = window.document,
    a = u.createElement("div"),
    f = /^((translate|rotate|scale)(X|Y|Z|3d)?|matrix(3d)?|perspective|skew(X|Y)?)$/i,
    l,
    c,
    h,
    p,
    d,
    v,
    m,
    g = {};
    e.each(o,
    function(e, i) {
        if (a.style[e + "TransitionProperty"] !== t) return n = "-" + b(e) + "-",
        r = i,
        !1
    }),
    l = n + "transform",
    g[c = n + "transition-property"] = g[h = n + "transition-duration"] = g[p = n + "transition-timing-function"] = g[d = n + "animation-name"] = g[v = n + "animation-duration"] = g[m = n + "animation-timing-function"] = "",
    e.fx = {
        off: r === t && a.style.transitionProperty === t,
        speeds: {
            _default: 400,
            fast: 200,
            slow: 600
        },
        cssPrefix: n,
        transitionEnd: w("TransitionEnd"),
        animationEnd: w("AnimationEnd")
    },
    e.fn.animate = function(t, n, r, i) {
        return e.isPlainObject(n) && (r = n.easing, i = n.complete, n = n.duration),
        n && (n = (typeof n == "number" ? n: e.fx.speeds[n] || e.fx.speeds._default) / 1e3),
        this.anim(t, n, r, i)
    },
    e.fn.anim = function(n, r, i, s) {
        var o, u = {},
        a, b = "",
        w = this,
        E, S = e.fx.transitionEnd;
        r === t && (r = .4),
        e.fx.off && (r = 0);
        if (typeof n == "string") u[d] = n,
        u[v] = r + "s",
        u[m] = i || "linear",
        S = e.fx.animationEnd;
        else {
            a = [];
            for (o in n) f.test(o) ? b += o + "(" + n[o] + ") ": (u[o] = n[o], a.push(y(o)));
            b && (u[l] = b, a.push(l)),
            r > 0 && typeof n == "object" && (u[c] = a.join(", "), u[h] = r + "s", u[p] = i || "linear")
        }
        return E = function(t) {
            if (typeof t != "undefined") {
                if (t.target !== t.currentTarget) return;
                e(t.target).unbind(S, E)
            }
            e(this).css(g),
            s && s.call(this)
        },
        r > 0 && this.bind(S, E),
        this.size() && this.get(0).clientLeft,
        this.css(u),
        r <= 0 && setTimeout(function() {
            w.each(function() {
                E.call(this)
            })
        },
        0),
        this
    },
    a = null
} (Zepto); (function(e) {
    function r(e) {
        return "tagName" in e ? e: e.parentNode
    }
    function i(e, t, n, r) {
        var i = Math.abs(e - t),
        s = Math.abs(n - r);
        return i >= s ? e - t > 0 ? "Left": "Right": n - r > 0 ? "Up": "Down"
    }
    function u() {
        o = null,
        t.last && (t.el.trigger("longTap"), t = {})
    }
    function a() {
        o && clearTimeout(o),
        o = null
    }
    var t = {},
    n, s = 750,
    o;
    e(document).ready(function() {
        var f, l;
        e(document.body).bind("touchstart",
        function(i) {
            f = Date.now(),
            l = f - (t.last || f),
            t.el = e(r(i.touches[0].target)),
            n && clearTimeout(n),
            t.x1 = i.touches[0].pageX,
            t.y1 = i.touches[0].pageY,
            l > 0 && l <= 250 && (t.isDoubleTap = !0),
            t.last = f,
            o = setTimeout(u, s)
        }).bind("touchmove",
        function(e) {
            a(),
            t.x2 = e.touches[0].pageX,
            t.y2 = e.touches[0].pageY
        }).bind("touchend",
        function(e) {
            a(),
            t.isDoubleTap ? (t.el.trigger("doubleTap"), t = {}) : t.x2 && Math.abs(t.x1 - t.x2) > 30 || t.y2 && Math.abs(t.y1 - t.y2) > 30 ? (t.el.trigger("swipe") && t.el.trigger("swipe" + i(t.x1, t.x2, t.y1, t.y2)), t = {}) : "last" in t && (t.el.trigger("tap"), n = setTimeout(function() {
                n = null,
                t.el.trigger("singleTap"),
                t = {}
            },
            250))
        }).bind("touchcancel",
        function() {
            n && clearTimeout(n),
            o && clearTimeout(o),
            o = n = null,
            t = {}
        })
    }),
    ["swipe", "swipeLeft", "swipeRight", "swipeUp", "swipeDown", "doubleTap", "tap", "singleTap", "longTap"].forEach(function(t) {
        e.fn[t] = function(e) {
            return this.bind(t, e)
        }
    })
})(Zepto); (function(e) {
    e.extend(e, {
        now: function() {
            return (new Date).getTime()
        },
        isNumeric: function(e) {
            return ! isNaN(parseFloat(e)) && isFinite(e)
        },
        getScript: function(t) {
            return e.ajaxJSONP({
                url: t
            })
        },
        noop: function() {},
        hideScroll: function() {
            var t = document.getElementsByTagName("body")[0],
            n = window.innerHeight;
            document.documentElement.clientHeight < n && (t.style.height = n + "px"),
            scrollTo(0, 0),
            setTimeout(function() {
                var t = e.os.ios && e.os.version.split(".")[0] * 1 == 5;
                if (t) {
                    var n = e(".pick-b,.pk3bar"),
                    r = {
                        bottom: 0,
                        left: 0
                    };
                    n.css("position", "absolute");
                    var i = function() {
                        n.css({
                            top: window.pageYOffset + (r.bottom !== undefined ? window.innerHeight - n.height() - r.bottom: r.top || 0),
                            left: r.right !== undefined ? document.body.offsetWidth - n.width() - r.right: r.left || 0
                        })
                    };
                    n && (i(), e(window).on("scroll", i), e(window).on("ortchange", i), e(window).on("touchmove", i))
                }
            },
            250)
        },
        goAnimatetop: function() {
            window.scrollTo(0, 0)
        },
        md5: function(e) {
            function t(e, t) {
                return e << t | e >>> 32 - t
            }
            function n(e, t) {
                var n, r, i, s, o;
                return i = e & 2147483648,
                s = t & 2147483648,
                n = e & 1073741824,
                r = t & 1073741824,
                o = (e & 1073741823) + (t & 1073741823),
                n & r ? o ^ 2147483648 ^ i ^ s: n | r ? o & 1073741824 ? o ^ 3221225472 ^ i ^ s: o ^ 1073741824 ^ i ^ s: o ^ i ^ s
            }
            function r(e, t, n) {
                return e & t | ~e & n
            }
            function i(e, t, n) {
                return e & n | t & ~n
            }
            function s(e, t, n) {
                return e ^ t ^ n
            }
            function o(e, t, n) {
                return t ^ (e | ~n)
            }
            function u(e, i, s, o, u, a, f) {
                return e = n(e, n(n(r(i, s, o), u), f)),
                n(t(e, a), i)
            }
            function a(e, r, s, o, u, a, f) {
                return e = n(e, n(n(i(r, s, o), u), f)),
                n(t(e, a), r)
            }
            function f(e, r, i, o, u, a, f) {
                return e = n(e, n(n(s(r, i, o), u), f)),
                n(t(e, a), r)
            }
            function l(e, r, i, s, u, a, f) {
                return e = n(e, n(n(o(r, i, s), u), f)),
                n(t(e, a), r)
            }
            function c(e) {
                var t, n = e.length,
                r = n + 8,
                i = (r - r % 64) / 64,
                s = (i + 1) * 16,
                o = Array(s - 1),
                u = 0,
                a = 0;
                while (a < n) t = (a - a % 4) / 4,
                u = a % 4 * 8,
                o[t] = o[t] | e.charCodeAt(a) << u,
                a++;
                return t = (a - a % 4) / 4,
                u = a % 4 * 8,
                o[t] = o[t] | 128 << u,
                o[s - 2] = n << 3,
                o[s - 1] = n >>> 29,
                o
            }
            function h(e) {
                var t = "",
                n = "",
                r, i;
                for (i = 0; i <= 3; i++) r = e >>> i * 8 & 255,
                n = "0" + r.toString(16),
                t += n.substr(n.length - 2, 2);
                return t
            }
            var p = Array(),
            d,
            v,
            m,
            g,
            y,
            b,
            w,
            E,
            S,
            x = 7,
            T = 12,
            N = 17,
            C = 22,
            k = 5,
            L = 9,
            A = 14,
            O = 20,
            M = 4,
            _ = 11,
            D = 16,
            P = 23,
            H = 6,
            B = 10,
            j = 15,
            F = 21;
            p = c(e),
            b = 1732584193,
            w = 4023233417,
            E = 2562383102,
            S = 271733878;
            for (d = 0; d < p.length; d += 16) v = b,
            m = w,
            g = E,
            y = S,
            b = u(b, w, E, S, p[d + 0], x, 3614090360),
            S = u(S, b, w, E, p[d + 1], T, 3905402710),
            E = u(E, S, b, w, p[d + 2], N, 606105819),
            w = u(w, E, S, b, p[d + 3], C, 3250441966),
            b = u(b, w, E, S, p[d + 4], x, 4118548399),
            S = u(S, b, w, E, p[d + 5], T, 1200080426),
            E = u(E, S, b, w, p[d + 6], N, 2821735955),
            w = u(w, E, S, b, p[d + 7], C, 4249261313),
            b = u(b, w, E, S, p[d + 8], x, 1770035416),
            S = u(S, b, w, E, p[d + 9], T, 2336552879),
            E = u(E, S, b, w, p[d + 10], N, 4294925233),
            w = u(w, E, S, b, p[d + 11], C, 2304563134),
            b = u(b, w, E, S, p[d + 12], x, 1804603682),
            S = u(S, b, w, E, p[d + 13], T, 4254626195),
            E = u(E, S, b, w, p[d + 14], N, 2792965006),
            w = u(w, E, S, b, p[d + 15], C, 1236535329),
            b = a(b, w, E, S, p[d + 1], k, 4129170786),
            S = a(S, b, w, E, p[d + 6], L, 3225465664),
            E = a(E, S, b, w, p[d + 11], A, 643717713),
            w = a(w, E, S, b, p[d + 0], O, 3921069994),
            b = a(b, w, E, S, p[d + 5], k, 3593408605),
            S = a(S, b, w, E, p[d + 10], L, 38016083),
            E = a(E, S, b, w, p[d + 15], A, 3634488961),
            w = a(w, E, S, b, p[d + 4], O, 3889429448),
            b = a(b, w, E, S, p[d + 9], k, 568446438),
            S = a(S, b, w, E, p[d + 14], L, 3275163606),
            E = a(E, S, b, w, p[d + 3], A, 4107603335),
            w = a(w, E, S, b, p[d + 8], O, 1163531501),
            b = a(b, w, E, S, p[d + 13], k, 2850285829),
            S = a(S, b, w, E, p[d + 2], L, 4243563512),
            E = a(E, S, b, w, p[d + 7], A, 1735328473),
            w = a(w, E, S, b, p[d + 12], O, 2368359562),
            b = f(b, w, E, S, p[d + 5], M, 4294588738),
            S = f(S, b, w, E, p[d + 8], _, 2272392833),
            E = f(E, S, b, w, p[d + 11], D, 1839030562),
            w = f(w, E, S, b, p[d + 14], P, 4259657740),
            b = f(b, w, E, S, p[d + 1], M, 2763975236),
            S = f(S, b, w, E, p[d + 4], _, 1272893353),
            E = f(E, S, b, w, p[d + 7], D, 4139469664),
            w = f(w, E, S, b, p[d + 10], P, 3200236656),
            b = f(b, w, E, S, p[d + 13], M, 681279174),
            S = f(S, b, w, E, p[d + 0], _, 3936430074),
            E = f(E, S, b, w, p[d + 3], D, 3572445317),
            w = f(w, E, S, b, p[d + 6], P, 76029189),
            b = f(b, w, E, S, p[d + 9], M, 3654602809),
            S = f(S, b, w, E, p[d + 12], _, 3873151461),
            E = f(E, S, b, w, p[d + 15], D, 530742520),
            w = f(w, E, S, b, p[d + 2], P, 3299628645),
            b = l(b, w, E, S, p[d + 0], H, 4096336452),
            S = l(S, b, w, E, p[d + 7], B, 1126891415),
            E = l(E, S, b, w, p[d + 14], j, 2878612391),
            w = l(w, E, S, b, p[d + 5], F, 4237533241),
            b = l(b, w, E, S, p[d + 12], H, 1700485571),
            S = l(S, b, w, E, p[d + 3], B, 2399980690),
            E = l(E, S, b, w, p[d + 10], j, 4293915773),
            w = l(w, E, S, b, p[d + 1], F, 2240044497),
            b = l(b, w, E, S, p[d + 8], H, 1873313359),
            S = l(S, b, w, E, p[d + 15], B, 4264355552),
            E = l(E, S, b, w, p[d + 6], j, 2734768916),
            w = l(w, E, S, b, p[d + 13], F, 1309151649),
            b = l(b, w, E, S, p[d + 4], H, 4149444226),
            S = l(S, b, w, E, p[d + 11], B, 3174756917),
            E = l(E, S, b, w, p[d + 2], j, 718787259),
            w = l(w, E, S, b, p[d + 9], F, 3951481745),
            b = n(b, v),
            w = n(w, m),
            E = n(E, g),
            S = n(S, y);
            var I = h(b) + h(w) + h(E) + h(S);
            return I.toLowerCase()
        },
        hashencrypt: function(t) {
            return e.md5(Q.lottery.hashkey + "|" + t + "|" + Q.lottery.hashkey)
        },
        serialize: function(t, n, r, i) {
            var s, o = e.isArray(n);
            e.each(n,
            function(n, u) {
                s = e.type(u),
                i && (n = r ? i: i + "[" + (o ? "": n) + "]"),
                !i && o ? t.add(u.name, u.value) : s == "array" || !r && s == "object" ? this.serialize(t, u, r, n) : t.add(n, u)
            })
        },
        paramUncoding: function(e, t) {
            var n = [];
            return n.add = function(e, t) {
                this.push(e + "=" + t)
            },
            this.serialize(n, e, t),
            n.join("&").replace(/%20/g, "+")
        }
    })
})(Zepto),
function(e, t) {
    var n = navigator.userAgent,
    r = navigator.appVersion,
    i = e.browser;
    e.extend(i, {
        "360se": /360/i.test(n),
        qq: /qq/i.test(n),
        uc: /UC/i.test(n) || /UC/i.test(r)
    }),
    i.uc = i.uc || !i.qq && !i.chrome && !i.firefox && !/safari/i.test(n);
    try {
        i.version = i.uc ? r.match(/UC(?:Browser)?\/([\d.]+)/)[1] : i.qq ? n.match(/MQQBrowser\/([\d.]+)/)[1] : i.version
    } catch(s) {}
    e.support = e.extend(e.support || {},
    {
        orientation: !(i.uc || parseFloat(e.os.version) < 5 && (i.qq || i.chrome)) && !(e.os.android && parseFloat(e.os.version) > 3) && "orientation" in window && "onorientationchange" in window,
        touch: "ontouchend" in document,
        cssTransitions: "WebKitTransitionEvent" in window,
        has3d: "WebKitCSSMatrix" in window && "m11" in new WebKitCSSMatrix
    })
} (Zepto),
function(e) {
    e.matchMedia = function() {
        var t = 0,
        n = "gmu-media-detect",
        r = e.fx.transitionEnd,
        i = e.fx.cssPrefix,
        s = e("<style></style>").append("." + n + "{" + i + "transition: width 0.001ms; width: 0; position: absolute; top: -10000px;}\n").appendTo("head");
        return function(i) {
            var o = n + t++,
            u, a = [],
            f;
            return s.append("@media " + i + " { #" + o + " { width: 1px; } }\n"),
            "matchMedia" in window ? window.matchMedia(i) : (u = e('<div class="' + n + '" id="' + o + '"></div>').appendTo("body").on(r,
            function() {
                f.matches = u.width() === 1,
                e.each(a,
                function(t, n) {
                    e.isFunction(n) && n.call(f, f)
                })
            }), f = {
                matches: u.width() === 1,
                media: i,
                addListener: function(e) {
                    return a.push(e),
                    this
                },
                removeListener: function(e) {
                    var t = a.indexOf(e);
                    return~t && a.splice(t, 1),
                    this
                }
            },
            f)
        }
    } ()
} (Zepto),
$(function() {
    $.mediaQuery = {
        ortchange: "screen and (width: " + window.innerWidth + "px)"
    },
    $.matchMedia($.mediaQuery.ortchange).addListener(function() {
        $(window).trigger("ortchange")
    })
}),
$(function() {
    $.SHAKE = {
        SHAKE_THRESHOLD: 2e3,
        last_x: 0,
        last_y: 0,
        last_z: 0,
        lastUpdate: 0,
        sharkTimer: null
    },
    $.deviceMotionHandler = function(e) {
        var t = $.SHAKE,
        n, r, i, s = e.accelerationIncludingGravity,
        o, u = (new Date).getTime();
        if (u - t.lastUpdate > 100) {
            var a = u - t.lastUpdate;
            t.lastUpdate = u,
            n = s.x,
            r = s.y,
            i = s.z;
            var f = Math.abs(n + r + i - t.last_x - t.last_y - t.last_z) / a * 1e4;
            f > t.SHAKE_THRESHOLD && (t.sharkTimer && clearTimeout(t.sharkTimer), t.sharkTimer = setTimeout(function() {
                Q.mobile.firedeviceShake()
            },
            300)),
            t.last_x = n,
            t.last_y = r,
            t.last_z = i
        }
    }
}); (function(e) {
    var t = e.$,
    n = e.Math,
    r = e.document,
    i = e.Date,
    s = {
        version: "0.0.1",
        system: {},
        number: {},
        date: {},
        array: {},
        localstorage: {},
        string: {},
        cookie: {},
        countdown: {},
        lightBox: {},
        ui: {},
        mobile: {},
        lottery: {},
        html5: {},
        sec: {}
    };
    s.system.isMobile = function() {
        var t = e.navigator.userAgent,
        n = t.length,
        r = t.replace(/iPhone|iPad|Android|webOS|hpwOS|TouchPad|Kindle|Series60|BlackBerry|Silk|\u4e91|Windows Phone/g, "").length;
        return r != n
    } (),
    s.system.getFirefoxVersion = function() {
        var t = e.navigator.userAgent.toLowerCase(),
        n = 0;
        return s.system.isMobile && t.indexOf("firefox") >= 0 && (n = t.replace(/^.*firefox\/(\d+)\..*$/gi, "$1")),
        n * 1
    } (),
    s.system.isAndroidUC = function() {
        var n = s.system.isMobile && t.browser.uc;
        return n && (e.alert = function(e) {
            s.lightBox.alert({
                content: e,
                confirmFn: function() {
                    this.close()
                }
            })
        }),
        n
    } (),
    s.system.orientation = function() {
        return s.system.isMobile && "orientation" in e ? !!e.orientation: !1
    },
    s.localstorage.isStorage = function() {
        try {
            return "localStorage" in e && e.localStorage !== null && (e.localStorage.testfirefox4mobile = !0) && !0
        } catch(t) {
            return ! 1
        }
    } (),
    s.localstorage.getItem = function(t) {
        return s.localstorage.isStorage ? e.localStorage[t] || "": s.cookie.get(t)
    },
    s.localstorage.setItem = function(t, n) {
        s.localstorage.isStorage ? e.localStorage[t] = n: s.cookie.set({
            name: t,
            value: n,
            expires: 1
        })
    },
    s.localstorage.removeItem = function(t) {
        s.localstorage.isStorage ? e.localStorage.removeItem(t) : s.cookie.set({
            name: t,
            value: "",
            expires: -1
        })
    },
    s.number.format = function(e, t, r) {
        var i;
        t = typeof(t * 1) != "number" || isNaN(t * 1) ? 2 : n.abs(t),
        i = n.pow(10, t),
        e *= 1;
        var o = 9.9999e-11;
        switch (r) {
        case 1:
            e = n.ceil(e * i) / i;
            break;
        case - 1 : e = n.floor(e * i + o) / i;
            break;
        case 465:
            var u = n.floor(e * i + o) % 10 % 2,
            a = n.floor(e * i * 10 + o) % 10 == 5,
            f = a && !u ? 1 / i: 0;
            e = s.number.format(e, t) - f;
            break;
        default:
            e = (e * i + o) / i
        }
        return (e.toFixed(t) + "").replace(/^\./g, "0.").replace(/\.$/, "")
    },
    s.number.formatCurrency = function(e, t, n) {
        var r;
        return typeof t != "undefined" && (e = s.number.format(e, t, n)),
        r = (e + "").split("."),
        r[0] = r[0].replace(/(\d)(?=(\d{3})+$)/g, "$1,"),
        (r[0] + (r.length == 2 ? "." + r[1] : "")).replace(/^\./g, "0.")
    },
    s.string.mulReplace = function(e, t) {
        for (var n = 0,
        r = t.length; n < r; n++) e = e.replace(t[n][0], t[n][1]);
        return e
    },
    s.date.format = function(e, t) {
        var n, r, o, u, a, f, l;
        return e = typeof e == "object" ? e: new i(e * 1),
        t = t || "YYYY-MM-DD hh:mm:ss",
        n = e.getFullYear(),
        o = e.getMonth() + 1,
        u = e.getDate(),
        a = e.getHours(),
        f = e.getMinutes(),
        l = e.getSeconds(),
        r = (n + "").replace(/^\d\d/g, ""),
        o = o < 10 ? "0" + o: o,
        u = u < 10 ? "0" + u: u,
        a = a < 10 ? "0" + a: a,
        f = f < 10 ? "0" + f: f,
        l = l < 10 ? "0" + l: l,
        s.string.mulReplace(t, [[/YYYY/, n], [/YY/, r], [/MM/, o], [/DD/, u], [/hh/, a], [/mm/, f], [/ss/, l]])
    },
    s.date.boundDay = function(e) {
        var t = new i,
        n, r, s;
        return t.setDate(t.getDate() + e),
        n = t.getFullYear(),
        r = t.getMonth() + 1,
        s = t.getDate(),
        r = r < 10 ? "0" + r: r,
        s = s < 10 ? "0" + s: s,
        n + "-" + r + "-" + s
    },
    s.date.toDate = function(e) {
        var n, r, s;
        return n = t.trim(e).split(" "),
        r = n[0].split(/[\-\/]/),
        s = n[1] ? n[1].split(":") : [0, 0, 0],
        new i(r[0], r[1] - 1, r[2], s[0], s[1], s[2])
    },
    s.cookie.get = function(e, t) {
        var n = r.cookie.match(new RegExp("(^| )" + e + "=([^;])*", "gi")),
        i = n ? n[0].split(e + "=")[1] : "";
        return t ? i: decodeURIComponent(i)
    },
    s.cookie.set = s.cookie.del = function(e) {
        var t = [];
        t.push(e.name + "="),
        e.value && t.push(e.encode ? e.value: encodeURIComponent(e.value));
        if (e.expires) {
            var n = new i;
            n.setHours(0),
            n.setMinutes(0),
            n.setSeconds(0),
            n.setTime(n.getTime() + e.expires * 864e5),
            t.push(";expires=" + n.toGMTString())
        }
        e.domain && t.push(";domain=" + e.domain),
        t.push(";path=" + (e.path || "/")),
        e.secure && t.push(";secure"),
        r.cookie = t.join("")
    },
    s.array.remove = function(e, t) {
        var n = e.indexOf(t);
        n > -1 && e.splice(n, 1)
    },
    s.number.aliquotsAndRemainder = function(e, t) {
        return [parseInt(e / t, 10), e % t]
    },
    s.number.perm = function(e, t) {
        if (e < t) return 0;
        var n = 1;
        for (var r = 0; r < t; r++) n *= e - r;
        return n
    },
    s.number.eachCombo = function(e, t) {
        var n = [];
        return function r(e, t, i) {
            if (i === 0) return n.push(e);
            for (var s = 0,
            o = t.length; s <= o - i; s++) r(e.concat(t[s]), t.slice(s + 1), i - 1)
        } ([], e, t),
        n
    },
    s.number.each_array_combo = function(e) {
        var t = 0,
        n = e.length,
        r = [],
        i,
        s = [];
        return s.push(e),
        function o(e) {
            var s = [];
            for (var u = 0,
            a = e.length; u < a; u++) for (var f = 0,
            l = e[u][t].length; f < l; f++) i = [].concat(e[u]),
            i.splice(t, 1, e[u][t][f]),
            s.push(i);
            t++;
            if (! (t < n)) {
                r = s;
                return
            }
            o(s)
        } (s),
        r
    },
    s.number.combo = function(e, t) {
        var n, r;
        e / 2 < t && (t = e - t);
        if (e < t || t < 0) return 0;
        if (e >= 0 && t === 0) return 1;
        n = 1,
        r = e;
        for (var i = 1; i <= t; i++) n *= i,
        i < t && (r *= e - i);
        return r / n
    },
    s.number.Cs = function(e, t) {
        var n = [];
        if (typeof e == "number") for (var r = 0; r < e; r++) n.push(r + 1);
        else n = e;
        var i = [];
        return function s(e, t, n) {
            if (n == 0) return i.push(e);
            for (var r = 0,
            o = t.length; r <= o - n; r++) s(e.concat(t[r]), t.slice(r + 1), n - 1)
        } ([], n, t),
        i
    },
    s.number.N1 = function(e, t) {
        var n = 0;
        if (t) {
            var r = e.length,
            i = s.number.Cs(r, t);
            for (var o = 0; o < i.length; o++) {
                var u = 1;
                for (var a = 0; a < i[o].length; a++) u *= e[i[o][a] - 1];
                n += u
            }
        } else n = 1;
        return n
    },
    s.number.N1d = function(e, t) {
        var n = [],
        r = [];
        try {
            for (var i = 0; i < e[1].length; i++) e[1][i] == 1 ? r.push(e[0][i]) : n.push(e[0][i])
        } catch(o) {
            return 0
        }
        return r.length <= t ? s.number.N1(n, t - r.length) * s.number.N1(r, r.length) : 0
    },
    s.number.N2SP = function(e, t) {
        var n = 0;
        if (t) {
            var r = e.length,
            i = s.number.Cs(r, t);
            for (var o = 0; o < i.length; o++) {
                var u = 1;
                for (var a = 0; a < i[o].length; a++) u *= e[i[o][a] - 1];
                n += u
            }
        } else n = 1;
        return n
    },
    s.number.N1SP = function(e, t, n) {
        var r = [],
        i = [];
        try {
            for (var o = 0; o < e[1].length; o++) e[1][o] == 1 ? i.push(e[0][o]) : r.push(e[0][o])
        } catch(u) {
            return 0
        }
        if (i.length <= t) {
            var a = 0,
            f = 1,
            l = s.number.Cs(r, t - i.length),
            c = s.number.Cs(i, i.length);
            for (var h = 0; h < c.length; h++) if (c[h]) for (var t = 0; t < c[h].length; t++) f *= c[h][t];
            for (var h = 0; h < l.length; h++) {
                var p = 1;
                for (var t = 0; t < l[h].length; t++) p *= l[h][t];
                var d = p * f * 2 * (s.mobile.backPerc || 1);
                d > 0 && d < 2 && (d = 2);
                var v = s.number.format(d, 3, -1);
                v = s.number.format(v, 2, 465),
                a += v * n
            }
            return a
        }
        return 0
    },
    s.number.sortNum = function(e, t) {
        var n = t != "desc" ?
        function(e, t) {
            return e - t
        }: function(e, t) {
            return t - e
        };
        return e instanceof Array ? e.sort(n) : e
    },
    s.number.random = function(e) {
        e = e || {
            min: 0,
            max: 9,
            size: 6
        },
        e.count = e.count || 1;
        var r = function(e) {
            var r, i, s = 0,
            o, u;
            o = (e.max + "").length,
            r = (e.share || []).toString(),
            r = r === "" ? [] : r.split(/[,\-_=+\|]/),
            u = r.length;
            if (u > 0 && e.max > 9) for (var a = 0; a < u; a++) r[a].length < o && (r[a] = "0000000000000000".substr(0, o - r[a].length) + r[a]);
            while (s < e.size) {
                i = n.floor(n.random() * (e.max - e.min + 1)) + e.min + "",
                i = "0000000000000000".substr(0, o - i.length) + i;
                if (e.repeat || !!e.repeat || t.inArray(i, r) == -1) r.push(i),
                s++
            }
            return ! e.sort || r.sort(),
            r
        },
        i = [];
        for (var s = 0; s < e.count; s++) i.push(r(e));
        return i
    },
    s.string.getUrlPath = function(t) {
        var n = e.location.protocol,
        r = e.location.host,
        i = "";
        n && r && (i = n + "//" + r);
        var s = t || "";
        return s && (/\?+/g.test(e.location.href) ? s = "&dest=" + s: s = "?dest=" + s),
        encodeURIComponent(e.location.href.replace(i, "") + s)
    },
    s.string.filterScript = function(e) {
        return e = e || "",
        e = decodeURIComponent(e),
        e = e.replace(/<.*>/g, ""),
        e = e.replace(/(java|vb|action)script/gi, ""),
        e = e.replace(/[\"\'][\s ]*([^=\"\'\s ]+[\s ]*=[\s ]*[\"\']?[^\"\']+[\"\']?)+/gi, ""),
        e
    },
    s.string.getUrlParam = function(t, n) {
        var r, i, s;
        return n = (n || e.location.href).split("#"),
        t.indexOf("#") != -1 ? s = n.length < 2 ? "": n[1] : s = n[0],
        r = s.match(new RegExp("(|[?&#])" + t.replace("#", "") + "=([^#&?]*)(\\s||$)", "gi")),
        r ? decodeURIComponent(r[0].split("=")[1]) : ""
    },
    s.string.getHash = function(e) {
        var t = e || location.hash;
        return t ? t.replace(/.*#/, "") : ""
    },
    s.string.getHashModelName = function(e) {
        var t = s.string.getHash();
        if (!t) return "";
        var n = t.split("&"),
        r;
        return e >= n.length ? r = n[0] : r = n[e],
        r.split("=")[0]
    },
    s.string.getHashActionName = function(e) {
        var t = s.string.getHash();
        if (!t) return "";
        var n = t.split("&"),
        r;
        return e >= n.length ? r = n[0] : r = n[e],
        r.split("=")[1]
    },
    s.countdown.start = function(e) {
        return new s.countdown.Go(e)
    },
    s.countdown.count = 0,
    s.countdown.freq = 6e5,
    s.countdown.toDHMS = function(e) {
        var t, n, r;
        return t = s.number.aliquotsAndRemainder(e * 1, 864e5),
        n = s.number.aliquotsAndRemainder(t[1], 36e5),
        r = s.number.aliquotsAndRemainder(n[1], 6e4),
        [t[0], n[0], r[0], parseInt(r[1] / 1e3, 10)]
    },
    s.countdown.ct = (e.systemTime * 1e3 || t.now()) - t.now(),
    s.countdown.updateCT = function(n, r) {
        var i = function() {
            t.ajax({
                url: (s.lottery.serverTimeUrl || "about:blank") + "?r=" + t.now(),
                error: function() {
                    s.countdown.__updt = setTimeout(i, s.countdown.freq)
                },
                success: function(r) {
                    s.countdown.ct = t.trim(r) * 1e3 - t.now(),
                    e.systemTime = r,
                    s.countdown.count > 0 && (s.countdown.__updt = setTimeout(i, s.countdown.freq)),
                    typeof n == "function" && n.call(null)
                }
            })
        };
        clearTimeout(s.countdown.__updt),
        i()
    },
    s.countdown.Go = function(e) {
        this.endTime = t.isNumeric(e.endTime) ? e.endTime * 1e3: s.date.toDate(e.endTime).getTime(),
        this.sid = e.sid,
        this.style = e.style || "DD\u5929hh\u5c0f\u65f6mm\u5206ss\u79d2",
        this.endStyle = e.endStyle || "\u8ba1\u65f6\u5df2\u7ed3\u675f",
        this.filter = e.filter || "",
        this.filled = e.filled,
        this.callback = typeof e.callback == "function" ? e.callback: t.noop,
        this.freq = (e.freq || s.countdown.freq) * 1,
        this.init()
    },
    s.countdown.Go.prototype = {
        init: function(e) {
            var r = this;
            s.countdown.count++,
            s.countdown.freq = n.min(this.freq, s.countdown.freq),
            s.countdown.updateCT(null, !0),
            function i() {
                var e, n;
                r.t = r.endTime - (t.now() + s.countdown.ct),
                r.t >= 1e3 ? (r.t < 2e3 && s.countdown.updateCT(null, !0), e = s.countdown.toDHMS(r.t), r.style.indexOf("DD") < 0 && (e[1] += e[0] * 24), r.style.indexOf("hh") < 0 && (e[2] += e[1] * 60), r.filled = typeof r.filled == "undefined" ? !0 : r.filled, r.filled && (e[1] = e[1] < 10 ? "0" + e[1] : e[1], e[2] = e[2] < 10 ? "0" + e[2] : e[2], e[3] = e[3] < 10 ? "0" + e[3] : e[3]), n = s.string.mulReplace(r.style, [[/DD/gi, e[0]], [/hh/gi, e[1]], [/mm/gi, e[2]], [/ss/gi, e[3]]]), typeof r.filter == "function" && (n = r.filter(n)), t(r.sid).html(n), setTimeout(i, 1e3)) : (t(r.sid).html(r.endStyle), s.countdown.count--, r.callback.call(r))
            } ()
        }
    },
    s.html5.translation = function(e, n, r) {
        var i = t.extend({
            duration: "0.3s",
            origin: "0 0"
        },
        n || {}),
        s = e.style; ! s.webkitTransitionProperty && (s.webkitTransitionProperty = "-webkit-transform"),
        s.webkitTransitionDuration !== i.duration && (s.webkitTransitionDuration = i.duration),
        s.webkitTransformOrigin !== i.origin && (s.webkitTransformOrigin = i.origin),
        s.webkitBackfaceVisibility !== "hidden" && (s.webkitBackfaceVisibility = "hidden"),
        s.webkitTransformStyle !== "preserve-3d" && (s.webkitTransformStyle = "preserve-3d");
        if (i.x != null || i.y != null) s.webkitTransform = "translate(" + (i.x ? i.x + "px,": "0,") + (i.y ? i.y + "px": "0") + ")",
        setTimeout(r, parseFloat(i.duration) * 1200)
    },
    s.number.pageHeight = function(e) {
        var t = r.body || r.documentElement;
        return /(height)/g.test(e) ? t.scrollHeight: t.scrollWidth
    },
    e.Q = e.qh360cp = s
})(window); (function() {
    function i(e) {
        return p(c(m(e)))
    }
    function s(e) {
        return d(c(m(e)))
    }
    function o(e, t) {
        return v(c(m(e)), t)
    }
    function u(e, t) {
        return p(h(m(e), m(t)))
    }
    function a(e, t) {
        return d(h(m(e), m(t)))
    }
    function f(e, t, n) {
        return v(h(m(e), m(t)), n)
    }
    function l() {
        return i("abc").toLowerCase() == "900150983cd24fb0d6963f7d28e17f72"
    }
    function c(e) {
        return w(E(b(e), e.length * 8))
    }
    function h(e, t) {
        var n = b(e);
        n.length > 16 && (n = E(n, e.length * 8));
        var r = Array(16),
        i = Array(16);
        for (var s = 0; s < 16; s++) r[s] = n[s] ^ 909522486,
        i[s] = n[s] ^ 1549556828;
        var o = E(r.concat(b(t)), 512 + t.length * 8);
        return w(E(i.concat(o), 640))
    }
    function p(e) {
        try {
            n
        } catch(t) {
            n = 0
        }
        var r = n ? "0123456789ABCDEF": "0123456789abcdef",
        i = "",
        s;
        for (var o = 0; o < e.length; o++) s = e.charCodeAt(o),
        i += r.charAt(s >>> 4 & 15) + r.charAt(s & 15);
        return i
    }
    function d(e) {
        try {
            r
        } catch(t) {
            r = ""
        }
        var n = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/",
        i = "",
        s = e.length;
        for (var o = 0; o < s; o += 3) {
            var u = e.charCodeAt(o) << 16 | (o + 1 < s ? e.charCodeAt(o + 1) << 8 : 0) | (o + 2 < s ? e.charCodeAt(o + 2) : 0);
            for (var a = 0; a < 4; a++) o * 8 + a * 6 > e.length * 8 ? i += r: i += n.charAt(u >>> 6 * (3 - a) & 63)
        }
        return i
    }
    function v(e, t) {
        var n = t.length,
        r, i, s, o, u, a = Array(Math.ceil(e.length / 2));
        for (r = 0; r < a.length; r++) a[r] = e.charCodeAt(r * 2) << 8 | e.charCodeAt(r * 2 + 1);
        var f = Math.ceil(e.length * 8 / (Math.log(t.length) / Math.log(2))),
        l = Array(f);
        for (i = 0; i < f; i++) {
            u = Array(),
            o = 0;
            for (r = 0; r < a.length; r++) {
                o = (o << 16) + a[r],
                s = Math.floor(o / n),
                o -= s * n;
                if (u.length > 0 || s > 0) u[u.length] = s
            }
            l[i] = o,
            a = u
        }
        var c = "";
        for (r = l.length - 1; r >= 0; r--) c += t.charAt(l[r]);
        return c
    }
    function m(e) {
        var t = "",
        n = -1,
        r, i;
        while (++n < e.length) r = e.charCodeAt(n),
        i = n + 1 < e.length ? e.charCodeAt(n + 1) : 0,
        55296 <= r && r <= 56319 && 56320 <= i && i <= 57343 && (r = 65536 + ((r & 1023) << 10) + (i & 1023), n++),
        r <= 127 ? t += String.fromCharCode(r) : r <= 2047 ? t += String.fromCharCode(192 | r >>> 6 & 31, 128 | r & 63) : r <= 65535 ? t += String.fromCharCode(224 | r >>> 12 & 15, 128 | r >>> 6 & 63, 128 | r & 63) : r <= 2097151 && (t += String.fromCharCode(240 | r >>> 18 & 7, 128 | r >>> 12 & 63, 128 | r >>> 6 & 63, 128 | r & 63));
        return t
    }
    function g(e) {
        var t = "";
        for (var n = 0; n < e.length; n++) t += String.fromCharCode(e.charCodeAt(n) & 255, e.charCodeAt(n) >>> 8 & 255);
        return t
    }
    function y(e) {
        var t = "";
        for (var n = 0; n < e.length; n++) t += String.fromCharCode(e.charCodeAt(n) >>> 8 & 255, e.charCodeAt(n) & 255);
        return t
    }
    function b(e) {
        var t = Array(e.length >> 2);
        for (var n = 0; n < t.length; n++) t[n] = 0;
        for (var n = 0; n < e.length * 8; n += 8) t[n >> 5] |= (e.charCodeAt(n / 8) & 255) << n % 32;
        return t
    }
    function w(e) {
        var t = "";
        for (var n = 0; n < e.length * 32; n += 8) t += String.fromCharCode(e[n >> 5] >>> n % 32 & 255);
        return t
    }
    function E(e, t) {
        e[t >> 5] |= 128 << t % 32,
        e[(t + 64 >>> 9 << 4) + 14] = t;
        var n = 1732584193,
        r = -271733879,
        i = -1732584194,
        s = 271733878;
        for (var o = 0; o < e.length; o += 16) {
            var u = n,
            a = r,
            f = i,
            l = s;
            n = x(n, r, i, s, e[o + 0], 7, parseInt("0xd76aa478")),
            s = x(s, n, r, i, e[o + 1], 12, parseInt("0xe8c7b756")),
            i = x(i, s, n, r, e[o + 2], 17, parseInt("0x242070db")),
            r = x(r, i, s, n, e[o + 3], 22, parseInt("0xc1bdceee")),
            n = x(n, r, i, s, e[o + 4], 7, parseInt("0xf57c0faf")),
            s = x(s, n, r, i, e[o + 5], 12, parseInt("0x4787c62a")),
            i = x(i, s, n, r, e[o + 6], 17, parseInt("0xa8304613")),
            r = x(r, i, s, n, e[o + 7], 22, parseInt("0xfd469501")),
            n = x(n, r, i, s, e[o + 8], 7, parseInt("0x698098d8")),
            s = x(s, n, r, i, e[o + 9], 12, parseInt("0x8b44f7af")),
            i = x(i, s, n, r, e[o + 10], 17, parseInt("0xffff5bb1")),
            r = x(r, i, s, n, e[o + 11], 22, parseInt("0x895cd7be")),
            n = x(n, r, i, s, e[o + 12], 7, parseInt("0x6b901122")),
            s = x(s, n, r, i, e[o + 13], 12, parseInt("0xfd987193")),
            i = x(i, s, n, r, e[o + 14], 17, parseInt("0xa679438e")),
            r = x(r, i, s, n, e[o + 15], 22, parseInt("0x49b40821")),
            n = T(n, r, i, s, e[o + 1], 5, parseInt("0xf61e2562")),
            s = T(s, n, r, i, e[o + 6], 9, parseInt("0xc040b340")),
            i = T(i, s, n, r, e[o + 11], 14, parseInt("0x265e5a51")),
            r = T(r, i, s, n, e[o + 0], 20, parseInt("0xe9b6c7aa")),
            n = T(n, r, i, s, e[o + 5], 5, parseInt("0xd62f105d")),
            s = T(s, n, r, i, e[o + 10], 9, parseInt("0x02441453")),
            i = T(i, s, n, r, e[o + 15], 14, parseInt("0xd8a1e681")),
            r = T(r, i, s, n, e[o + 4], 20, parseInt("0xe7d3fbc8")),
            n = T(n, r, i, s, e[o + 9], 5, parseInt("0x21e1cde6")),
            s = T(s, n, r, i, e[o + 14], 9, parseInt("0xc33707d6")),
            i = T(i, s, n, r, e[o + 3], 14, parseInt("0xf4d50d87")),
            r = T(r, i, s, n, e[o + 8], 20, parseInt("0x455a14ed")),
            n = T(n, r, i, s, e[o + 13], 5, parseInt("0xa9e3e905")),
            s = T(s, n, r, i, e[o + 2], 9, parseInt("0xfcefa3f8")),
            i = T(i, s, n, r, e[o + 7], 14, parseInt("0x676f02d9")),
            r = T(r, i, s, n, e[o + 12], 20, parseInt("0x8d2a4c8a")),
            n = N(n, r, i, s, e[o + 5], 4, parseInt("0xfffa3942")),
            s = N(s, n, r, i, e[o + 8], 11, parseInt("0x8771f681")),
            i = N(i, s, n, r, e[o + 11], 16, parseInt("0x6d9d6122")),
            r = N(r, i, s, n, e[o + 14], 23, parseInt("0xfde5380c")),
            n = N(n, r, i, s, e[o + 1], 4, parseInt("0xa4beea44")),
            s = N(s, n, r, i, e[o + 4], 11, parseInt("0x4bdecfa9")),
            i = N(i, s, n, r, e[o + 7], 16, parseInt("0xf6bb4b60")),
            r = N(r, i, s, n, e[o + 10], 23, parseInt("0xbebfbc70")),
            n = N(n, r, i, s, e[o + 13], 4, parseInt("0x289b7ec6")),
            s = N(s, n, r, i, e[o + 0], 11, parseInt("0xeaa127fa")),
            i = N(i, s, n, r, e[o + 3], 16, parseInt("0xd4ef3085")),
            r = N(r, i, s, n, e[o + 6], 23, parseInt("0x04881d05")),
            n = N(n, r, i, s, e[o + 9], 4, parseInt("0xd9d4d039")),
            s = N(s, n, r, i, e[o + 12], 11, parseInt("0xe6db99e5")),
            i = N(i, s, n, r, e[o + 15], 16, parseInt("0x1fa27cf8")),
            r = N(r, i, s, n, e[o + 2], 23, parseInt("0xc4ac5665")),
            n = C(n, r, i, s, e[o + 0], 6, parseInt("0xf4292244")),
            s = C(s, n, r, i, e[o + 7], 10, parseInt("0x432aff97")),
            i = C(i, s, n, r, e[o + 14], 15, parseInt("0xab9423a7")),
            r = C(r, i, s, n, e[o + 5], 21, parseInt("0xfc93a039")),
            n = C(n, r, i, s, e[o + 12], 6, parseInt("0x655b59c3")),
            s = C(s, n, r, i, e[o + 3], 10, parseInt("0x8f0ccc92")),
            i = C(i, s, n, r, e[o + 10], 15, parseInt("0xffeff47d")),
            r = C(r, i, s, n, e[o + 1], 21, parseInt("0x85845dd1")),
            n = C(n, r, i, s, e[o + 8], 6, parseInt("0x6fa87e4f")),
            s = C(s, n, r, i, e[o + 15], 10, parseInt("0xfe2ce6e0")),
            i = C(i, s, n, r, e[o + 6], 15, parseInt("0xa3014314")),
            r = C(r, i, s, n, e[o + 13], 21, parseInt("0x4e0811a1")),
            n = C(n, r, i, s, e[o + 4], 6, parseInt("0xf7537e82")),
            s = C(s, n, r, i, e[o + 11], 10, parseInt("0xbd3af235")),
            i = C(i, s, n, r, e[o + 2], 15, parseInt("0x2ad7d2bb")),
            r = C(r, i, s, n, e[o + 9], 21, parseInt("0xeb86d391")),
            n = k(n, u),
            r = k(r, a),
            i = k(i, f),
            s = k(s, l)
        }
        return Array(n, r, i, s)
    }
    function S(e, t, n, r, i, s) {
        return k(L(k(k(t, e), k(r, s)), i), n)
    }
    function x(e, t, n, r, i, s, o) {
        return S(t & n | ~t & r, e, t, i, s, o)
    }
    function T(e, t, n, r, i, s, o) {
        return S(t & r | n & ~r, e, t, i, s, o)
    }
    function N(e, t, n, r, i, s, o) {
        return S(t ^ n ^ r, e, t, i, s, o)
    }
    function C(e, t, n, r, i, s, o) {
        return S(n ^ (t | ~r), e, t, i, s, o)
    }
    function k(e, t) {
        var n = (e & 65535) + (t & 65535),
        r = (e >> 16) + (t >> 16) + (n >> 16);
        return r << 16 | n & 65535
    }
    function L(e, t) {
        return e << t | e >>> 32 - t
    }
    var e = window.$,
    t = window.qh360cp;
    t.sec = t.sec || {};
    var n = 0,
    r = "";
    t.sec.md5 = t.sec.hex_md5 = function(e) {
        return i(e)
    },
    t.sec.b64_md5 = function(e) {
        return s(e)
    },
    t.sec.str_md5 = function(e) {
        return str_md5(e)
    },
    t.sec.hex_hmac_md5 = function(e, t) {
        return u(e, t)
    },
    t.sec.b64_hmac_md5 = function(e, t) {
        return a(e, t)
    },
    t.sec.str_hmac_md5 = function(e, t) {
        return str_hmac_md5(e, t)
    },
    t.sec.md5_test_abc = function() {
        return i("abc") == "900150983cd24fb0d6963f7d28e17f72"
    }
})(); (function(e) {
    var t = e.$,
    n = e.qh360cp,
    r = function(e) {
        this.maskColor = "#fff",
        this.opacity = 1,
        this.html = "",
        this.domMain = null,
        this.domMask = null,
        this.id = "",
        this.closeSel = "",
        this.cancelSel = "",
        this.confrimSel = "",
        this.closeFn = t.noop,
        this.cancelFn = t.noop,
        this.confirmFn = t.noop,
        this.showFn = t.noop,
        this.init(e)
    };
    r.prototype = {
        constructor: r,
        init: function(e) {
            t.extend(this, e),
            this._create(),
            this._events()
        },
        _create: function() {
            var e, r;
            this.close(),
            t("body").append('<div name="m360mobile" id="mobilepop" class="popup"></div>'),
            t("body").append('<div name="m360mobile" id="mobilemask">'),
            this.domMain = r = t("#mobilepop"),
            this.domMask = e = t("#mobilemask"),
            r.html(this.html),
            t.goAnimatetop(),
            setTimeout(function() {
                t("#mobilemask").height(n.number.pageHeight("height") + "px")
            },
            200)
        },
        _events: function() {
            var e = this;
            this.closeSel && this.domMain.find(this.closeSel).on({
                click: function() {
                    typeof e.closeFn == "function" && e.closeFn.call(e),
                    e.close()
                }
            }),
            this.confirmSel && this.domMain.find(this.confirmSel).on({
                click: function() {
                    typeof e.confirmFn == "function" && e.confirmFn.call(e)
                },
                touchstart: function() {
                    t(this).addClass("btntap")
                },
                touchend: function() {
                    t(this).removeClass("btntap")
                }
            }),
            this.cancelSel && this.domMain.find(this.cancelSel).on({
                click: function() {
                    typeof e.cancelFn == "function" && e.cancelFn.call(e),
                    e.close()
                },
                touchstart: function() {
                    t(this).addClass("btntap")
                },
                touchend: function() {
                    t(this).removeClass("btntap")
                }
            }),
            typeof this.showFn == "function" && this.showFn.call(this)
        },
        close: function() {
            this.domMask !== null && t("div[name=m360mobile]").remove()
        }
    },
    n.lightBox.show = function(e) {
        return new r(e)
    },
    n.lightBox.login = function(r) {
        var i = ['<div class="head">', '  <div class="head-tit">\u767b\u5f55360\u5e10\u53f7</div>', '  <a href="javascript:;" class="cls">\u5173\u95ed x</a>', "</div>", '<div class="popupc loginlayout">', '<div class="area" id="login">', '  <div id="loginForm">', '	<div id="LoginWrap"> ', '	  <div id="errorTips"><span id="qt_global_text">\u8bf7\u8f93\u5165\u60a8\u7684\u624b\u673a\u53f7/\u90ae\u7bb1/\u7528\u6237\u540d</span></div>', '	  <ul class="fieldinner">', '		  <li class="username">', '			<input type="text" name="qt_account" id="qt_account" placeholder="\u8bf7\u8f93\u5165\u624b\u673a\u53f7/\u90ae\u7bb1/\u7528\u6237\u540d" class="ipt-txt">', '			<div class="del_touch"><span class="del_u"></span></div>', "		  </li>", '		  <li class="pwd">', '			<input type="password" name="qt_password" id="qt_password" placeholder="\u8bf7\u8f93\u5165\u5bc6\u7801" class="ipt-txt">', "		  </li>", '		  <li class="auth-code">', '			<input type="text" name="qt_phrase" id="qt_phrase" placeholder="\u8bf7\u8f93\u5165\u9a8c\u8bc1\u7801" class="auth-code-txt" maxlength="7">', '			<img class="check-code-img" src="##" id="qt_phrasecode">', "		  </li>", "		  <li>", '			<p id="wait1" class="red none">\u6b63\u5728\u767b\u5f55\u4e2d(<cite id="second">20</cite>),\u8bf7\u7a0d\u540e......</p>', '			<p id="wait2" class="red none">\u7531\u4e8e\u7f51\u7edc\u8d85\u65f6,\u8bf7\u7a0d\u540e\u518d\u8bd5\uff01</p>', "		  </li>", "	  </ul>", '	  <div id="iskeepalive"><label for="qtis_autologin"><input type="checkbox" id="qtis_autologin" tabindex="5" checked="checked" value="1">\u4e0b\u6b21\u81ea\u52a8\u767b\u5f55</label></div>', '	  <div class="submit-cont submit-reg">', '		<input class="submit" type="button" id="qt_btn" value="\u767b  \u5f55">', '		<input id="qt_btn_reg" class="sub-reg" type="submit" value="\u6ce8  \u518c">', "	  </div>", '	  <div class="login-option"><a href="#" id="findpwd" class="fr blue font14" id="forget">\u5fd8\u8bb0\u5bc6\u7801\uff1f</a><a href="#" class="blue font14" id="forget">\u5fd8\u8bb0\u5e10\u53f7\uff1f</a></div>', "	</div>", " </div>", "</div>", "</div>"].join(""),
        s = n.string.getUrlPath("next");
        n.lightBox.show({
            html: i,
            closeSel: ".cls",
            cancelSel: "#qt_btn_reg",
            cancelFn: function() {
                e.location.href = "/passport/reg?redirect_url=" + s
            }
        }),
        typeof r == "function" && (n.lightBox.loginCallback = function() {
            r.call(null)
        }),
        t("#mobilemask").get(0).style.cssText = "background:#ededed;";
        var o = n.cookie.get("mpw_account"),
        u = n.cookie.get("from"),
        a = "mpw_caipiao" + (u ? "_tg_" + u: ""),
        f = null,
        l = "none",
        c = t("#wait1"),
        h = t("#wait2"),
        p = t("#second"),
        d = t(".del_touch");
        o && (t("#qt_account").val(o), d.show());
        var v = t.trim(t("#qt_account").val()),
        m = "";
        v && (m = "&account=" + encodeURIComponent(v)),
        t("#findpwd").attr("href", "http://i.360.cn/findpwdwap?skin=red" + m + "&destUrl=" + encodeURIComponent(e.location.href)),
        t("#forget").attr("href", "/passport/findaccount?redirect_url=" + s),
        n.mobile.waittimer = null,
        n.mobile.resolve = 1;
        var g = "";
        QHPass.mShowLogin(function() {
            var e = t.trim(t("#qt_account").val());
            n.cookie.set({
                name: "mpw_account",
                value: e,
                expires: 365
            }),
            n.lightBox.close(),
            typeof n.lightBox.loginCallback == "function" && n.lightBox.loginCallback()
        },
        {
            doms: {
                account: "qt_account",
                password: "qt_password",
                phrase: "qt_phrase",
                isAutologin: "qtis_autologin",
                globalTips: "qt_global_text",
                phraseImg: "qt_phrasecode"
            },
            extFun: {
                init: function() {
                    var e = t("#qt_btn"),
                    n = t("#qt_account"),
                    r = t("#qt_password"),
                    i = t("#qt_phrase"),
                    s = t("#qt_phrasecode");
                    e.on({
                        click: function() {
                            var e = t(this);
                            e.val("\u767b\u5f55\u4e2d..."),
                            c.addClass(l),
                            h.addClass(l),
                            QHPass.loginUtils.submit()
                        },
                        touchstart: function() {
                            t(this).addClass("btntap")
                        },
                        touchend: function() {
                            t(this).removeClass("btntap")
                        }
                    }),
                    n.on({
                        keydown: function(e) {
                            e.keyCode == 13 && r.focus()
                        },
                        keyup: function() {
                            var e = t(this);
                            t.trim(e.val()) ? d.show() : d.hide()
                        }
                    }),
                    r.on("keydown",
                    function(e) {
                        e.keyCode == 13 && (t("#LoginWrap .auth-code").css("display") !== "none" ? i.focus() : QHPass.loginUtils.submit())
                    }),
                    i.on("keydown",
                    function(e) {
                        e.keyCode == 13 && QHPass.loginUtils.submit()
                    }),
                    s.on("click",
                    function() {
                        QHPass.loginUtils.setPhrase(g)
                    })
                },
                phrase: function(e) {
                    t("#LoginWrap .auth-code").show(),
                    t("#qt_phrase").focus(),
                    g = e,
                    QHPass.loginUtils.setPhrase(e)
                },
                error: function(e) {
                    n.mobile.resolve = 0,
                    clearTimeout(n.mobile.waittimer),
                    p.text("20"),
                    c.addClass(l),
                    t("#qt_btn").val("\u767b  \u5f55");
                    var r = t("#errorTips");
                    r.css("top", "45%"),
                    f && clearTimeout(f),
                    f = setTimeout(function() {
                        r.css("top", "-40px")
                    },
                    2e3),
                    QHPass.loginUtils.setPhrase(g)
                },
                correct: function(e) {},
                before: function() {
                    setTimeout(function() {
                        if (n.mobile.resolve) {
                            clearTimeout(n.mobile.waittimer);
                            var e = t("#qt_btn"),
                            r = 20;
                            n.mobile.waittimer = setInterval(function() {
                                c.removeClass(l),
                                h.addClass(l),
                                r--,
                                p.text(r),
                                r <= 1 && (h.removeClass(l), c.addClass(l), e.val("\u767b  \u5f55"), clearTimeout(n.mobile.waittimer))
                            },
                            1e3)
                        }
                    },
                    4e3)
                },
                loading: function() {},
                after: function() {},
                httpsTimeout: function() {
                    t.ajax({
                        url: "/int/dolog?name=https&detail=loginhttpsTimeout&r=" + t.now(),
                        error: function() {},
                        success: function() {
                            console.log("https \u767b\u5f55\u8d85\u65f6")
                        }
                    })
                },
                httpTimeout: function() {
                    t.ajax({
                        url: "/int/dolog?name=http&detail=loginhttpTimeout&r=" + t.now(),
                        error: function() {},
                        success: function() {
                            console.log("http \u767b\u5f55\u8d85\u65f6")
                        }
                    })
                }
            },
            phraseTime: "2",
            domainList: ["360"],
            src: a,
            captchaAppId: "caipiaoWap"
        }),
        d.on(n.lottery.tap,
        function() {
            t("#qt_account").val(""),
            t(this).hide()
        })
    },
    n.lightBox.close = function(e) {
        return t("div[name=m360mobile]").remove(),
        typeof e == "function" && e.call(null),
        !1
    },
    n.lightBox.alert = function(e) {
        var t = [];
        return t.push('<div class="pop-box"><div class="pop-boxt"><h2>' + (e.title || "\u6e29\u99a8\u63d0\u793a") + '</h2><a href="javascript:;" class="cls">X</a></div><div class="pop-boxc"><div class="pop-ts">' + e.content + '</div></div><div class="pop-boxb"><button type="button" class="btn confirm">' + (e.ensure || "\u786e \u5b9a") + "</button></div></div>"),
        e.confirmSel = ".confirm",
        e.html = t.join(""),
        e.closeSel = ".cls",
        n.lightBox.show(e)
    },
    n.lightBox.confirm = function(e) {
        var t = [];
        return t.push('<div class="pop-box"><div class="pop-boxt"><h2>' + (e.title || "\u6e29\u99a8\u63d0\u793a") + '</h2><a href="javascript:;" class="cls">X</a></div><div class="pop-boxc"><div class="pop-ts">' + e.content + '</div></div><div class="pop-boxb pop-f"><button type="button" class="btn sure">' + (e.sure || "\u786e\u8ba4") + '</button><button type="button" class="btn btn-gray off">' + (e.off || "\u53d6\u6d88") + '</button></div><div class="pay-exp"></div></div>'),
        e.confirmSel = ".sure",
        e.cancelSel = ".off",
        e.html = t.join(""),
        e.closeSel = ".cls",
        n.lightBox.show(e)
    },
    n.lightBox.pageLoading = {
        open: function() {
            t("body").append(t('<div name="mloading360mobile" class="load_wrap"><div class="css3_loading" id="css3_loading"></div></div><div name="mloading360mobile" class="mask" id="mobile-pagemask">')),
            setTimeout(function() {
                t("#mobile-pagemask").height(n.number.pageHeight("height") + "px")
            },
            120)
        },
        close: function(e) {
            return t("div[name=mloading360mobile]").remove(),
            typeof e == "function" && e.call(null),
            !1
        }
    }
})(window); (function(e) {
    var t = e.$ || {},
    n = e.qh360cp || {};
    n.lottery.debug = !0,
    n.lottery.APIUrls = n.lottery.debug ? "https://cp.360.cn": "http://ons.new.caipiao.360.cn",
    n.lottery.APIUrl = n.lottery.debug ? "http://cp.360.cn": "http://ons.new.caipiao.360.cn",
    n.lottery.APIChart = n.lottery.debug ? "http://chart.cp.360.cn": "http://ons.chart.cp.360.cn",
    n.lottery.APIHelper = n.lottery.debug ? "http://huodong.cp.360.cn": "http://ons.huodong.cp.360.cn",
    n.lottery.APILeitai = n.lottery.debug ? "http://leitai.cp.360.cn": "http://ons.leitai.cp.360.cn",
    n.lottery.APIOdds = n.lottery.debug ? "http://odds.cp.360.cn": "http://ons.odds.cp.360.cn",
    n.lottery.tap = t.support.touch ? "tap": "click",
    n.lottery.hashkey = "fcfa5d2e",
    n.lottery.defaultMssage = "\u586b\u5199\u4fe1\u606f\u6709\u8bef!",
    n.lottery.price = 2,
    n.lottery.maxMoney = 5e6,
    n.lottery.payBuyMoney = 0,
    n.lottery.postSuccessBackUrl = "",
    n.lottery.serverTimeUrl = "/int/getcurtime/",
    n.lottery.special = /\/?(bt)\/(join|coop|trc)$/,
    n.lottery.routeHighlottery = /^(166406|168009|255401|258001|258203|255903|257503|165707|166407|166507|165207|255803)$/,
    n.lottery.lotType = {
        ssq: 220051,
        sfc: 130011,
        rj: 130019,
        dlt: 120029,
        p3: 110033,
        p5: 110035,
        qx: 110022,
        sd: 210053,
        qlc: 220028,
        syy: 166406,
        sscjx: 258001,
        ssccq: 255401,
        jczq: 130042,
        jclq: 130043,
        dc: 130041,
        worldcup: 140045,
        gyj: 140046,
        kl8: 265108,
        dlc: 168009,
        hbk3: 257703,
        jsk3: 255903,
        jlk3: 258203,
        k3nm: 257503,
        k3gx: 255803,
        gd11: 165707,
        sh11: 165207,
        xw: 223515,
        pk3: 166407,
        hlj11: 166507
    },
    n.lottery.payResultcode = {
        1329 : "\u652f\u4ed8\u5bc6\u7801\u9519\u8bef",
        1351 : "\u60a8\u586b\u5199\u7684\u4fe1\u606f\u548c\u5f53\u521d\u53c2\u4e0e\u6d3b\u52a8\u8bbe\u7f6e\u4e0d\u7b26\uff0c\u8bf7\u91cd\u65b0\u586b\u5199\u3002\u6709\u95ee\u9898\u8bf7\u81f4\u7535010-59059560\uff01",
        1314 : "\u5df2\u586b\u5199\u8fc7\u5b9e\u540d\u4fe1\u606f",
        1319 : "\u8d26\u6237\u767b\u5f55\u5bc6\u7801\u9519\u8bef",
        1328 : "\u652f\u4ed8\u5bc6\u7801\u9519\u8bef\u6b21\u6570\u8d85\u9650\uff0c\u9700\u898115\u5206\u949f\u540e\u518d\u8bd5",
        1320 : "\u652f\u4ed8\u5bc6\u7801\u4e0d\u80fd\u4e0e\u767b\u5f55\u5bc6\u7801\u76f8\u540c",
        1318 : "\u4e24\u6b21\u8f93\u5165\u7684\u5bc6\u7801\u4e0d\u4e00\u81f4",
        1305 : "\u5fc5\u9700\u53c2\u6570\u4e0d\u80fd\u4e3a\u7a7a",
        "1332:": "\u5df2\u586b\u5199\u8fc7\u652f\u4ed8\u5bc6\u7801",
        1339 : "\u767b\u5f55\u5bc6\u7801\u9519\u8bef\u6b21\u6570\u8d85\u9650",
        1322 : "\u7b7e\u540d\u9519\u8bef",
        9003 : "\u7528\u6237\u672a\u767b\u5f55",
        1824 : "\u59d3\u540d\u4e0e\u94f6\u884c\u5361\u59d3\u540d\u4fe1\u606f\u4e0d\u7b26",
        1805 : "\u6bcf\u5929\u53ea\u80fd\u63d0\u6b3e\u4e00\u6b21\uff0c\u8bf7\u60a8\u660e\u5929\u518d\u6765\u63d0\u6b3e\uff01",
        1711 : "\u59d3\u540d\u4e0e\u7528\u6237\u4fe1\u606f\u771f\u5b9e\u59d3\u540d\u4e0d\u7b26",
        1709 : "\u672a\u7ed1\u5b9a\u94f6\u884c\u5361",
        1710 : "\u5df2\u7ed1\u5b9a\u94f6\u884c\u5361",
        1330 : "\u7b54\u6848\u6216\u8005\u9a8c\u8bc1\u7801\u9519\u8bef",
        1331 : "\u8f93\u5165\u7b54\u6848\u6216\u8005\u9a8c\u8bc1\u7801\u9519\u8bef\u6b21\u6570\u8fc7\u591a\uff0c\u9700\u91cd\u65b0\u83b7\u53d6\u9a8c\u8bc1\u7801",
        1806 : "\u63d0\u73b0\u91d1\u989d\u5927\u4e8e\u8d26\u6237\u4f59\u989d",
        1807 : "\u63d0\u73b0\u5931\u8d25",
        1336 : "\u4fee\u6539\u624b\u673a\u53f7\u5931\u8d25",
        1335 : "\u65b0\u624b\u673a\u53f7\u4e0e\u65e7\u624b\u673a\u53f7\u53f7\u7801\u76f8\u540c",
        1337 : "\u65e7\u624b\u673a\u53f7\u7801\u9519\u8bef",
        1602 : "\u65b0\u589e\u652f\u4ed8\u8ba2\u5355\u5931\u8d25",
        1601 : "\u53c2\u6570\u9519\u8bef",
        1605 : "\u652f\u4ed8\u6263\u6b3e\u6210\u529f\uff0c\u4f46\u901a\u77e5\u5f69\u7968\u5931\u8d25",
        1610 : "\u8ba2\u5355\u5f02\u5e38\uff0c\u4e0d\u80fd\u652f\u4ed8",
        1611 : "\u8be5\u8ba2\u5355\u5df2\u7ecf\u652f\u4ed8\u8fc7",
        1612 : "\u8be5\u8ba2\u5355\u76ee\u524d\u4e0d\u53ef\u652f\u4ed8",
        1613 : "\u8ba2\u5355\u8d85\u65f6\u4e0d\u80fd\u652f\u4ed8\uff0c\u8bf7\u91cd\u65b0\u63d0\u4ea4\u8ba2\u5355",
        1112 : "\u8d26\u6237\u672a\u5b8c\u5168\u6fc0\u6d3b",
        1303 : "\u5b89\u5168\u95ee\u9898\u7b54\u6848\u9519\u8bef",
        1304 : "\u91cd\u7f6e\u5bc6\u7801\u5931\u8d25",
        1332 : "\u60a8\u5df2\u7ecf\u586b\u5199\u8fc7\u652f\u4ed8\u5bc6\u7801\uff0c\u8bf7\u52ff\u91cd\u590d\u586b\u5199",
        1317 : "\u8bc1\u4ef6\u53f7\u7801\u4e0d\u7b26\u5408\u89c4\u5b9a",
        6002 : "\u8ddd\u79bb\u4e0a\u6b21\u53d1\u9001\u65f6\u95f4\u8fc7\u77ed",
        6003 : "\u60a8\u5df2\u8fbe\u5230\u89c4\u5b9a\u65f6\u95f4\u5185\u7684\u6700\u5927\u53d1\u9001\u6b21\u6570",
        1006 : "qid\u9519\u8bef",
        1607 : "\u7b49\u5f85\u65f6\u95f4\u8d85\u957f\uff0c\u8bf7\u91cd\u65b0\u652f\u4ed8",
        1619 : "\u7b7e\u540d\u9519\u8bef",
        6002 : "\u8ddd\u79bb\u4e0a\u6b21\u53d1\u9001\u65f6\u95f4\u8fc7\u77ed",
        6003 : "\u60a8\u5df2\u8fbe\u5230\u89c4\u5b9a\u65f6\u95f4\u5185\u7684\u6700\u5927\u53d1\u9001\u6b21\u6570",
        6004 : "\u624b\u673a\u9a8c\u8bc1\u7801\u9519\u8bef",
        6005 : "\u624b\u673a\u9a8c\u8bc1\u7801\u9519\u8bef\u6b21\u6570\u8fc7\u591a\uff0c\u8bf7\u91cd\u65b0\u83b7\u53d6",
        6006 : "\u624b\u673a\u9a8c\u8bc1\u7801\u9519\u8bef",
        6007 : "\u5f53\u65e5\u77ed\u4fe1\u53d1\u9001\u6570\u76ee\u592a\u591a"
    },
    n.lottery.lotReffer = {
        220051 : "ssq",
        120029 : "slt",
        210053 : "sd",
        110033 : "p3",
        110035 : "p5",
        110022 : "qxc",
        220028 : "qlc",
        166406 : "syy",
        168009 : "dlc",
        258001 : "xssc",
        255401 : "lssc",
        258203 : "k3jl",
        130043 : "jclq",
        130042 : "jczq",
        130041 : "bjdc",
        130011 : "sf",
        130019 : "rj",
        165707 : "gd11",
        165207 : "sh11",
        257503 : "k3nm",
        255903 : "k3js",
        223515 : "xw",
        255803 : "k3gx",
        166407 : "pk3",
        166507 : "hlj11",
        140045 : "worldcup",
        140046 : "gyj"
    },
    n.lottery.lotTypeCn = {
        220051 : "\u53cc\u8272\u7403",
        130011 : "\u80dc\u8d1f\u5f69",
        130019 : "\u4efb\u9009\u4e5d",
        120029 : "\u5927\u4e50\u900f",
        110033 : "\u6392\u5217\u4e09",
        110035 : "\u6392\u5217\u4e94",
        110022 : "\u4e03\u661f\u5f69",
        210053 : "\u798f\u5f693D",
        220028 : "\u4e03\u4e50\u5f69",
        166406 : "11\u90095",
        168009 : "\u65b011\u90095",
        258001 : "\u65b0\u65f6\u65f6\u5f69",
        255401 : "\u8001\u65f6\u65f6\u5f69",
        130042 : "\u7ade\u5f69\u8db3\u7403",
        265108 : "\u5feb\u4e508",
        130041 : "\u5317\u4eac\u5355\u573a",
        130043 : "\u7ade\u5f69\u7bee\u7403",
        257703 : "\u5feb3",
        255903 : "\u8001\u5feb3",
        258203 : "\u65b0\u5feb3",
        257503 : "\u5feb3",
        165707 : "\u5e7f\u4e1c11\u90095",
        165207 : "\u4e0a\u6d7711\u90095",
        223515 : "15\u90095",
        166407 : "\u5feb\u4e50\u6251\u514b",
        166507 : "\u5e78\u8fd011\u90095",
        140045 : "\u4e16\u754c\u676f\u51a0\u519b",
        140046 : "\u4e16\u754c\u676f\u51a0\u4e9a\u519b",
        255803 : "\u597d\u8fd0\u5feb3"
    },
    n.mobile.getServerTimes = function(e) {
        t.ajax({
            url: "/int/getcurtime?r=" + t.now(),
            error: function() {
                typeof e == "function" && e(0)
            },
            success: function(t) {
                t = t * 1 > 0 ? t * 1 : 0,
                typeof e == "function" && e(t)
            }
        })
    },
    n.lottery.ajaxData = function(r, i) {
        o = r.BetType || r.buyType;
        
        var u = e.location.pathname,
        a = n.lottery.special.test(u),
        f = "";
        switch (o) {
        case "joinbuy":
            s = "/int/join/",
            f = "/bt/join";
            break;
        case "team":
            f = "/bt/coop";
            break;
        case "trc":
            f = ""
        }
        n.lottery.postSuccessBackUrl = f ? u.replace(/\/$/g, "") + f: u,
        n.lottery.payBuyMoney = (r.BetType == "team" ? r.SponsorBuy * 1 + r.LockCount * 1 : r.BetMoney) || r.BuyMoney || r.TotalMoney,
        t.ajax({
            url: f,
            data: t.param(r) + "&r=" + t.now(),
            dataType: "json",
            type: "POST",
            success: function(e) {
                n.lottery.chkStatus(e, i)
            },
            error: function() {
                n.lightBox.alert({
                    content: '<p class="msg">\u7f51\u7edc\u8d85\u65f6\uff0c\u8bf7\u7a0d\u540e\u518d\u8fdb\u884c\u6295\u6ce8\uff01</p>',
                    confirmFn: function() {
                        this.close()
                    },
                    cancelFn: function() {
                        this.close()
                    }
                }),
                n.lottery.recoveryInit()
            }
        })
    },
    n.lottery.recoveryInit = function() {
        t("#pay_buy").prop("disabled", !1).removeClass("btnoff").text("\u7acb\u523b\u4ed8\u6b3e")
    },
    n.lottery.disabledInit = function() {
        t("#pay_buy").prop("disabled", !0).addClass("btnoff").text("\u5904\u7406\u4e2d...")
    },
    n.lottery.chkStatus = function(t, r) {
        var i = t.xCode * 1,
        s, o, u = n.lottery.postSuccessBackUrl || e.location.pathname;
        switch (i) {
        case 0:
            !t.xValue || (s = t.xValue.OrderID, o = t.xValue.URL);
            var a = o.match(/(\d)$/g)[0] * 1 || 1,
            f = t.xValue.LotID || n.mobile.lotType;
            e.location.href = "/pay?orderid=" + s + "&paychan=" + a + "&url=" + u + "&lotID=" + f;
            break;
        case 1:
            !t.xValue || (s = t.xValue.OrderID, o = t.xValue.URL),
            n.lightBox.alert({
                content: '<p class="msg">\u5e10\u53f7\u4f59\u989d\u4e0d\u8db3\uff0c\u8bf7\u5148\u5145\u503c\uff01</p>',
                confirmFn: function() {
                    n.lottery.recoveryInit();
                    var r = o.match(/(\d)$/g)[0] * 1 || 1,
                    i = t.xValue.LotID || n.mobile.lotType,
                    a = "http://m.cp.360.cn/pay?orderid=" + s + "&paychan=" + r + "&url=" + u + "&lotID=" + i;
                    e.location.href = "/recharge#" + n.lottery.payBuyMoney + "@" + a
                },
                closeFn: function() {
                    n.lottery.recoveryInit()
                }
            });
            break;
        case 2:
            e.location.href = "/profile/pasvpay?url=" + u + "?dest=next";
            break;
        case 100:
            n.lightBox.login(r),
            n.lottery.recoveryInit();
            break;
        default:
            n.lottery.recoveryInit(),
            n.lightBox.alert({
                content: '<p class="msg">' + t.xMessage || "\u672a\u77e5\u9519\u8bef</p>",
                confirmFn: function() {
                    this.close()
                },
                cancelFn: function() {
                    this.close()
                }
            })
        }
    },
    n.lottery.errMsg = function(e, n) {
        var r = t("#error_" + e),
        i = "none";
        t(".err").html(""),
        n ? r.removeClass(i).html(n) : r.addClass(i).html("")
    },
    n.lottery.dealPlay = function(e) {
        var t = "";
        return e.length && (e[0] == e[1] && e[1] == e[2] ? t = "(\u8c79\u5b50)": e[0] != e[1] && e[0] != e[2] && e[1] != e[2] ? t = "(\u7ec4\u516d)": t = "(\u7ec4\u4e09)"),
        t
    },
    n.lottery.dealpk3 = function(e) {
        var n;
        t.type(e) == "string" ? n = e.split(" ") : n = e;
        var r = [n[0].substring(0, 1), n[1].substring(0, 1), n[2].substring(0, 1)],
        i = [n[0].substring(1), n[1].substring(1), n[2].substring(1)],
        s = function(e, t) {
            var n = Array.prototype.indexOf;
            if (n && e.indexOf === n) return e.indexOf(t) != -1
        },
        o = [],
        u = [];
        t.each(i,
        function(e, t) {
            s(u, t) || (u.push(t), o.push(i[e]))
        });
        var a = "",
        f = "",
        l = 0,
        c = 0;
        o.length === 1 ? a = "\u8c79\u5b50": o.length === 2 && (a = "\u5bf9\u5b50"),
        r[0] === r[1] && r[0] === r[2] && (l = 1);
        var h = ["A23", "234", "345", "456", "567", "678", "789", "890", "90J", "0JQ", "JQK", "AQK"];
        return h.indexOf(i.join("")) != -1 && (c = 1),
        l && c ? (a = "\u540c\u82b1\u987a", f = r[0]) : l ? (a = "\u540c\u82b1", f = r[0]) : c && (a = "\u987a\u5b50"),
        [r, i, a, f]
    },
    n.lottery.calPosition = function(n, r) {
        var i = {},
        s = t(e),
        o = [s.width(), s.height()];
        return i.left = "50%",
        i.top = o[1] / 2,
        i.marginLeft = "-" + n / 2 + "px",
        i.marginTop = "-80px",
        i
    },
    n.lottery.alert = function(e) {
        var r = null;
        r && clearTimeout(r);
        var i = t('<div id="alertBox">' + e + "</div>");
        t("body").append(i),
        setTimeout(function() {
            i.css(n.lottery.calPosition(i.width(), i.height()))
        },
        20),
        r = setTimeout(function() {
            t("#alertBox").remove()
        },
        2e3)
    },
    n.lottery.getencrypt = function(e, t) {
        var n = e.length,
        r = Math.ceil(t.length / n),
        i = "";
        for (var s = 0; s < r; s++) {
            var o = t.substring(s * n, (s + 1) * n),
            u = n;
            o.length < n && (u = o.length);
            for (var a = 0; a < u; a++) i += String.fromCharCode(e.charCodeAt(a) ^ o.charCodeAt(a))
        }
        return i
    },
    n.lottery.requireToken = function(e) {
        var r = t("#token");
        t.ajax({
            url: n.lottery.APIUrl + "/qpayapissl/requiretoken?r=" + t.now(),
            dataType: "jsonp",
            success: function(t) {
                t && t.result_code == "9999" && (r.val(t.pptoken), e && e())
            },
            error: function() {
                n.lottery.requireToken.call(null, e)
            }
        })
    },
    n.lottery.validateCard = function(e) {
        try {
            if (e.length == 0 || e.length < 12 || e.length > 19) return ! 1;
            var t = /[34569]/,
            r = new RegExp(t);
            if (r.test(e.charAt(0) == 0)) return ! 1;
            var i = n.lottery.reverse(e),
            s = 0,
            o = 0;
            for (var u = 0; u < i.length; u++) if (u % 2 == 0) s += i.charAt(u) * 1;
            else {
                var a = i.charAt(u) * 2;
                a > 9 ? o = o + (a / 10 | 0) + a % 10 : o += a
            }
            var f = s + o;
            return f % 10 == 0 ? !0 : !1
        } catch(l) {
            return ! 1
        }
    },
    n.lottery.reverse = function(e) {
        var t = e,
        n = "";
        for (m = t.length - 1; m >= 0; m--) n += t.charAt(m);
        return n
    },
    n.lottery.getActionTarget = function(e, t, n, r) {
        var i = e.target,
        s = t || 3,
        o = t !== -1,
        u = n || "cmd",
        a = r || document.body;
        if (i === a) return i.getAttribute(u) ? i: null;
        while (i && i !== a && (o ? s-->0 : !0)) {
            if (i.getAttribute(u)) return i;
            i = i.parentNode
        }
        return null
    }
})(window); (function(e) {
    $(function() {
        var t = /http:\/\/([\w-]+\.)+([\w-]+\.)(360\.)(?:cn|com)/g,
        n = /\/lotdetails\/(coopbuy|appendbuy|ownbuy)\//g,
        r = /\/lotdetails\/(coopbuy|appendbuy|ownbuy|buycoop)\//g,
        i = /(\?)?(redirect_url=).*/g,
        s = /\/(profile|recharge|pay)[\/\?]?/g,
        o = /360browser/g,
        u = /lotid\/(\d{6})\/?/,
        a = encodeURIComponent(e.location.href.replace(t, "").replace(i, "")),
        f = $(".head-r"),
        l = $("#model-u"),
        c = $(".gotop"),
        h = e.location.href.split(/\?/)[0],
        p = "/passport/login/?redirect_url=",
        d = function(e) {
            var t = $("#tools").find("li"),
            n = t.eq(0),
            r = t.eq(0).find("a"),
            i = t.eq(2),
            s = t.eq(2).find("a"),
            o = t.eq(3),
            u = t.eq(3).find("a"),
            a = t.eq(5),
            f = t.eq(5).find("a"),
            l = "none";
            e == "220051" && a.removeClass(l),
            e == "265108" && n.addClass(l);
            if (/^(13004)[123]$/g.test(e)) {
                var c = {
                    130043 : ["/jclq", "/kaijiang/jclqkj"],
                    130042 : ["/jczq", "/kaijiang/fballkj"],
                    130041 : ["/dc", "/kaijiang/bjdckj"]
                };
                r.attr("href", c[e][0]),
                u.attr("href", c[e][1])
            } else {
                var p = "";
                /^(13001)[1|9]$/g.test(e) && (p = "zc/"),
                r.attr("href", "/" + p + Q.lottery.lotReffer[e]),
                u.attr("href", "/kaijiang/thistory/?lotid=" + e)
            }
            /(buycoop|coopbuy)/g.test(h) && (i.removeClass(l), o.addClass(l), s.attr("href", "/coophall/" + Q.lottery.lotReffer[e] + "/"))
        };
        if (r.test(h) && !/(MSSQ|MBJD)\d{15,}/g.test(h)) {
            $("body").append('<div class="pop-box3 none" id="tools"><div class="pop-box2-bar"></div><div class="filt-popc"><ul><li><a href="#" class="btn-pop">\u8d2d\u4e70\u8be5\u5f69\u79cd</a></li><li><a href="/lotteryhall" class="btn-pop">\u8d2d\u5f69\u5927\u5385</a></li><li class="none"><a href="#" class="btn-pop">\u53c2\u4e0e\u5408\u4e70</a></li><li><a href="#" id="history" class="btn-pop">\u5386\u53f2\u5f00\u5956</a></li><li><a href="/profile/" class="btn-pop">\u6211\u7684\u5f69\u7968</a></li><li class="none"><a href="/gallery/" class="btn-pop">\u53cc\u8272\u7403\u9891\u9053</a></li></ul></div></div>'),
            $(".head").append('<a href="javascript:;" class="btn-menu">\u2261</a>');
            var v = setTimeout(function() {
                $(".btn-menu").on(Q.lottery.tap,
                function() {
                    var e = $("#tools"),
                    t = $(this),
                    n = "btn-menu-on",
                    r = "none";
                    t.hasClass(n) ? (e.addClass(r), t.removeClass(n)) : (e.removeClass(r), t.addClass(n))
                })
            },
            400),
            m;
            if (u.test(h)) m = h.match(u)[1],
            d(m);
            else var g = $("#product"),
            y = setInterval(function() {
                m = g.attr("lotid"),
                m && (d(m), clearInterval(y))
            },
            200)
        }
        var b = {
            landing: ['<a href="#" class="btn-h aboutlg">\u767b\u5f55</a>|<a href="#" class="btn-h aboutrg">\u6ce8\u518c</a>', '<a href="#" class="btn-12 aboutlg">\u767b\u5f55</a><a href="#" class="btn-12 aboutrg">\u6ce8\u518c</a><a href="/recharge/" class="btn-12">\u5145\u503c</a><a href="/profile/withdraw" class="btn-12">\u63d0\u6b3e</a>'],
            notlogged: ['<a href="/profile/" class="btn-11">\u6211\u7684\u5f69\u7968</a>', '<span><a href="javascript:;" id="mequit" class="blue">[\u9000\u51fa]</a></span><a href="/profile/" class="blue aboutme">{%aboutme%}</a>']
        };
        if (!Q.cookie.get("Q")) {
            if (s.test(h) || n.test(h)) e.location.href = p + a;
            f.html(b.landing[0]),
            l.html(b.landing[1]),
            $(".aboutlg").attr("href", "/passport/login/?redirect_url=" + a),
            $(".aboutrg").attr("href", "/passport/reg/?redirect_url=" + a)
        } else f.html(b.notlogged[0]),
        $.ajax({
            url: "/int/getcuruser?r=" + $.now(),
            dataType: "json",
            success: function(e) {
                e && l.html(b.notlogged[1].replace(/{%aboutme%}/g, e))
            }
        });
        c.on("click",
        function() {
            setTimeout(function() {
                e.scrollTo(0, 0)
            },
            200)
        });
        var w = !1;
        $.os.android && (parseFloat($.os.version.substring(0, 3)) < 4 ? w = !0 : w = !1);
        if (!w) {
            var E = $("#sheader");
            E.show();
            var S = [0, 78, 135];
            $(e).on("scroll",
            function() {
                var t = e.scrollY;
                E.css({
                    "-webkit-transform": "translate(0, -" + (t > S[1] ? S[1] : S[2]) + "px)"
                })
            }),
            $(".head-btn").on("click",
            function() {
                var e = $(this),
                t = e.attr("status") || "close";
                t == "close" ? (e.attr("status", "open"), E.css({
                    "-webkit-transform": "translate(0, -0px)"
                })) : (e.attr("status", "close"), E.css({
                    "-webkit-transform": "translate(0, -78px)"
                }))
            })
        }
        var x = Q.string.getUrlParam("from");
        /(360se|ucweb|dolphin|oupeng|dingyuan)$/g.test(x) && Q.cookie.set({
            name: "from",
            value: x,
            expires: 0
        });
        var T = Q.string.getUrlParam("agent");
        T && Q.cookie.set({
            name: "cp_360_agent",
            value: T,
            expires: 7
        }),
        typeof QHPass != "undefined" && (QHPass.resConfig.reloadAfterLogout = !1, l.on("click", "#mequit",
        function() {
            QHPass.logout()
        })),
        e.addEventListener("load",
        function(t) {
            $.hideScroll(),
            e.applicationCache && (e.applicationCache.addEventListener("error",
            function() {
                console.log("Error: Cache failed to update!")
            },
            !1), e.applicationCache.addEventListener("updateready",
            function(t) {
                e.applicationCache.status == e.applicationCache.UPDATEREADY && (e.applicationCache.swapCache(), e.location.reload())
            },
            !1))
        },
        !1)
    })
})(window);
function is(e, t) {
    return typeof e === t
}
function testProps(e, t) {
    for (var n in e) if (mStyle[e[n]] !== undefined) return t == "pfx" ? e[n] : !0;
    return ! 1
}
function testPropsAll(e, t, n) {
    var r = e.charAt(0).toUpperCase() + e.substr(1),
    i = (e + " " + cssomPrefixes.join(r + " ") + r).split(" ");
    if (is(t, "string") || is(t, "undefined")) return testProps(i, t)
}
var css3 = {},
docElement = document.documentElement,
mod = "modernizr",
injectElementWithStyles = function(e, t, n, r) {
    var i, s, o, u = document.createElement("div"),
    a = document.body,
    f = a ? a: document.createElement("body");
    if (parseInt(n, 10)) while (n--) o = document.createElement("div"),
    o.id = r ? r[n] : mod + (n + 1),
    u.appendChild(o);
    return i = ["&#173;", '<style id="s', mod, '">', e, "</style>"].join(""),
    u.id = mod,
    (a ? u: f).innerHTML += i,
    f.appendChild(u),
    a || (f.style.background = "", docElement.appendChild(f)),
    s = t(u, e),
    a ? u.parentNode.removeChild(u) : f.parentNode.removeChild(f),
    !!s
},
cssomPrefixes = "Webkit Moz O ms".split(" "),
mStyle = docElement.style;
css3.hasTransform = function() {
    return !! testPropsAll("transform")
},
css3.has3d = function() {
    var e = !!testPropsAll("perspective");
    return e && "webkitPerspective" in docElement.style && injectElementWithStyles("@media (transform-3d),(-webkit-transform-3d){#modernizr{left:9px;position:absolute;height:3px;}}",
    function(t, n) {
        e = t.offsetLeft === 9 && t.offsetHeight === 3
    }),
    e
};
var isAndroid = /android/gi.test(navigator.appVersion),
has3d = css3.has3d(),
hasTransform = css3.hasTransform(),
gv1 = has3d ? "translate3d(": "translate(",
gv2 = has3d ? ",0)": ")";
$.touchSlider = function(e, t) {
    if (!e) return null;
    t ? t.container = e: t = typeof e == "string" ? {
        container: e
    }: e,
    $.extend(this, {
        container: ".slider",
        wrap: null,
        panel: null,
        trigger: null,
        activeTriggerCls: "sel",
        hasTrigger: !0,
        hasTouch: !0,
        steps: 0,
        left: 0,
        reduce: 0,
        visible: 1,
        margin: 0,
        curIndex: 0,
        len: 3,
        duration: 500,
        loop: !1,
        play: !1,
        interval: 5e3,
        useTransform: !isAndroid,
        isEvent: !0,
        callback: null,
        prev: null,
        next: null,
        activePnCls: "none"
    },
    t),
    this.initialize()
},
$.extend($.touchSlider.prototype, {
    initialize: function() {
        this.findEl() && this.init(),
        this.isEvent && this.initEvent()
    },
    reset: function(e) {
        $.extend(this, e || {}),
        this.init(),
        this.isEvent ? this.initEvent() : this.destroy()
    },
    findEl: function() {
        var e = this.container = $(this.container);
        return e.length ? (this.wrap = this.wrap && e.find(this.wrap) || e.children().first(), this.wrap.length ? (this.panel = this.panel && e.find(this.panel) || this.wrap.children().first(), this.panel.length ? (this.panels = this.panel.children(), this.panels.length ? (this.trigger = this.trigger && e.find(this.trigger), this.prev = this.prev && e.find(this.prev), this.next = this.next && e.find(this.next), this) : (this.container.hide(), null)) : null) : null) : null
    },
    init: function() {
        var e = this.wrap,
        t = this.panel,
        n = this.container,
        r = this.panels,
        i = this.trigger,
        s = this.len || r.length,
        o = this.margin,
        u = 0,
        a = this.visible,
        f = this.useTransform = hasTransform ? this.useTransform: !1,
        l = (this.fact ? this.fact.width() : e.width()) - this.reduce;
        this.steps = l;
        var c = l;
        r.each(function(e, t) {
            $(t).css("width", c),
            u += c
        }),
        o && typeof o == "number" && (u += (s - 1) * o, this.steps += o),
        a > 1 && (this.loop = !1);
        var h = this.left;
        h -= this.curIndex * this.steps,
        this.setCoord(t, h),
        f && (e.css({
            "-webkit-transform": "translate3d(0,0,0)"
        }), t.css({
            "-webkit-backface-visibility": "hidden"
        }), r.css({
            "-webkit-transform": gv1 + "0,0" + gv2
        }));
        var p = this._pages = Math.ceil(s / a);
        this._minpage = 0,
        this._maxpage = this._pages - 1;
        if (p <= 1) return i && i.hide(),
        null;
        if (this.loop) {
            t.append(r[0].cloneNode(!0));
            var d = r[s - 1].cloneNode(!0);
            t.append(d),
            d.style.cssText += "position:relative;left:" + -this.steps * (s + 2) + "px;",
            u += r[0].offsetWidth,
            u += r[s - 1].offsetWidth
        }
        t.css("width", u);
        if (i && i.length) {
            var v = "",
            m = i.children();
            if (!m.length) {
                for (var g = 0; g < p; g++) v += "<span" + (g == this.curIndex ? " class=" + this.activeTriggerCls + "": "") + "></span>";
                i.html(v)
            }
            this.triggers = i.children(),
            this.triggerSel = this.triggers[this.curIndex]
        } else this.hasTrigger = !1;
        return this.update(),
        this
    },
    initEvent: function() {
        var e = this,
        t = e.wrap[0],
        n = e.prev,
        r = e.next,
        i = e.triggers;
        t.addEventListener && e.hasTouch && (t.addEventListener("touchstart", e, !1), t.addEventListener("touchmove", e, !1), t.addEventListener("touchend", e, !1), t.addEventListener("webkitTransitionEnd", e, !1)),
        e.play && e.begin(),
        n && n.length && n.on("click",
        function(t) {
            e.backward.call(e, t)
        }),
        r && r.length && r.on("click",
        function(t) {
            e.forward.call(e, t)
        }),
        e.hasTrigger && i && i.each(function(t, n) {
            $(n).on("click",
            function() {
                e.slideTo(t)
            })
        }),
        e.isEvent = 1,
        $(window).on("ortchange",
        function() {
            e.init()
        }),
        $(window).on("resize",
        function() {
            e.init()
        })
    },
    handleEvent: function(e) {
        switch (e.type) {
        case "touchstart":
            this.start(e);
            break;
        case "touchmove":
            this.move(e);
            break;
        case "touchend":
        case "touchcancel":
            this.end(e);
            break;
        case "webkitTransitionEnd":
            this.transitionEnd(e)
        }
    },
    start: function(e) {
        var t = e.touches[0];
        this._movestart = undefined,
        this._disX = 0,
        this._coord = {
            x: t.pageX,
            y: t.pageY
        }
    },
    move: function(e) {
        if (e.touches.length > 1 || e.scale && e.scale !== 1) return;
        var t = e.touches[0],
        n = this._disX = t.pageX - this._coord.x,
        r = this.left,
        i;
        typeof this._movestart == "undefined" && (this._movestart = !!(this._movestart || Math.abs(n) < Math.abs(t.pageY - this._coord.y))),
        this._movestart || (e.preventDefault(), this.stop(), this.loop || (n /= !this.curIndex && n > 0 || this.curIndex == this._maxpage && n < 0 ? Math.abs(n) / this.steps + 1 : 1), i = r - this.curIndex * this.steps + n, this.setCoord(this.panel, i), this._disX = n)
    },
    end: function(e) {
        if (!this._movestart) {
            var t = this._disX;
            t < -10 ? (e.preventDefault(), this.forward()) : t > 10 && (e.preventDefault(), this.backward())
        }
    },
    backward: function(e) {
        e && e.preventDefault && e.preventDefault();
        var t = this.curIndex,
        n = this._minpage;
        t -= 1,
        t < n && (this.loop ? t = n - 1 : t = n),
        this.slideTo(t)
    },
    forward: function(e) {
        e && e.preventDefault && e.preventDefault();
        var t = this.curIndex,
        n = this._maxpage;
        t += 1,
        t > n && (this.loop ? t = n + 1 : t = n),
        this.slideTo(t)
    },
    setCoord: function(e, t) {
        this.useTransform && e.css("-webkit-transform", gv1 + t + "px,0" + gv2) || e.css("left", t)
    },
    slideTo: function(e, t) {
        e = e || 0,
        this.curIndex = e;
        var n = this.panel,
        r = n[0].style,
        i = this.left - e * this.steps;
        r.webkitTransitionDuration = (t || this.duration) + "ms",
        this.setCoord(n, i),
        this.update(),
        !this.hasTouch && this.callback && this.callback()
    },
    transitionEnd: function() {
        var e = this.panel,
        t = e[0].style,
        n = this.loop,
        r = this.curIndex;
        n && (r > this._maxpage ? this.curIndex = 0 : r < this._minpage && (this.curIndex = this._maxpage), this.setCoord(e, this.left - this.curIndex * this.steps)),
        !n && r == this._maxpage ? (this.stop(), this.play = !1) : this.begin(),
        this.update(),
        t.webkitTransitionDuration = t.transitionDuration = 0,
        this.hasTouch && this.callback && this.callback()
    },
    update: function() {
        var e = this.triggers,
        t = this.activeTriggerCls,
        n = this.curIndex;
        e && e[n] && (this.triggerSel && (this.triggerSel.className = ""), e.removeClass(t), e[n].className = t, this.triggerSel = e[n])
    },
    updateArrow: function() {
        var e = this.prev,
        t = this.next;
        if (!e || !e.length || !t || !t.length) return;
        if (this.loop) return;
        var n = this.curIndex,
        r = this.activePnCls;
        n <= 0 && e.addClass(r) || e.removeClass(r),
        n >= this._maxpage && t.addClass(r) || t.removeClass(r)
    },
    begin: function() {
        var e = this;
        e.play && !e._playTimer && (e.stop(), e._playTimer = setInterval(function() {
            e.forward()
        },
        e.interval))
    },
    stop: function() {
        var e = this;
        e.play && e._playTimer && (clearInterval(e._playTimer), e._playTimer = null)
    },
    destroy: function() {
        var e = this,
        t = e.wrap[0],
        n = e.prev,
        r = e.next,
        i = e.triggers;
        t.removeEventListener && e.hasTouch && (t.removeEventListener("touchstart", e, !1), t.removeEventListener("touchmove", e, !1), t.removeEventListener("touchend", e, !1), t.removeEventListener("webkitTransitionEnd", e, !1)),
        n && n.length && n.off("click"),
        r && r.length && r.off("click"),
        e.hasTrigger && i && i.each(function(e, t) {
            $(t).off("click")
        }),
        e.isEvent = 0
    }
});
/*! * iScroll v4.2.5 ~ Copyright (c) 2012 Matteo Spinelli, http://cubiq.org * Released under MIT license, http://cubiq.org/license */
(function(e, t) {
    function A(e) {
        return i === "" ? e: (e = e.charAt(0).toUpperCase() + e.substr(1), i + e)
    }
    var n = Math,
    r = t.createElement("div").style,
    i = function() {
        var e = "t,webkitT,MozT,msT,OT".split(","),
        t,
        n = 0,
        i = e.length;
        for (; n < i; n++) {
            t = e[n] + "ransform";
            if (t in r) return e[n].substr(0, e[n].length - 1)
        }
        return ! 1
    } (),
    s = i ? "-" + i.toLowerCase() + "-": "",
    o = A("transform"),
    u = A("transitionProperty"),
    a = A("transitionDuration"),
    f = A("transformOrigin"),
    l = A("transitionTimingFunction"),
    c = A("transitionDelay"),
    h = /android/gi.test(navigator.appVersion),
    p = /iphone|ipad/gi.test(navigator.appVersion),
    d = /hp-tablet/gi.test(navigator.appVersion),
    v = A("perspective") in r,
    m = "ontouchstart" in e && !d,
    g = i !== !1,
    y = A("transition") in r,
    b = "onorientationchange" in e ? "orientationchange": "resize",
    w = m ? "touchstart": "mousedown",
    E = m ? "touchmove": "mousemove",
    S = m ? "touchend": "mouseup",
    x = m ? "touchcancel": "mouseup",
    T = function() {
        if (i === !1) return ! 1;
        var e = {
            "": "transitionend",
            webkit: "webkitTransitionEnd",
            Moz: "transitionend",
            O: "otransitionend",
            ms: "MSTransitionEnd"
        };
        return e[i]
    } (),
    N = function() {
        return e.requestAnimationFrame || e.webkitRequestAnimationFrame || e.mozRequestAnimationFrame || e.oRequestAnimationFrame || e.msRequestAnimationFrame ||
        function(e) {
            return setTimeout(e, 1)
        }
    } (),
    C = function() {
        return e.cancelRequestAnimationFrame || e.webkitCancelAnimationFrame || e.webkitCancelRequestAnimationFrame || e.mozCancelRequestAnimationFrame || e.oCancelRequestAnimationFrame || e.msCancelRequestAnimationFrame || clearTimeout
    } (),
    k = v ? " translateZ(0)": "",
    L = function(n, r) {
        var i = this,
        c;
        i.wrapper = typeof n == "object" ? n: t.getElementById(n),
        i.wrapper.style.overflow = "hidden",
        i.scroller = i.wrapper.children[0],
        i.options = {
            hScroll: !0,
            vScroll: !0,
            x: 0,
            y: 0,
            bounce: !0,
            bounceLock: !1,
            momentum: !0,
            lockDirection: !0,
            useTransform: !0,
            useTransition: !1,
            topOffset: 0,
            checkDOMChanges: !1,
            handleClick: !0,
            hScrollbar: !0,
            vScrollbar: !0,
            fixedScrollbar: h,
            hideScrollbar: p,
            fadeScrollbar: p && v,
            scrollbarClass: "",
            zoom: !1,
            zoomMin: 1,
            zoomMax: 4,
            doubleTapZoom: 2,
            wheelAction: "scroll",
            snap: !1,
            snapThreshold: 1,
            onRefresh: null,
            onBeforeScrollStart: function(e) {
                e.preventDefault()
            },
            onScrollStart: null,
            onBeforeScrollMove: null,
            onScrollMove: null,
            onBeforeScrollEnd: null,
            onScrollEnd: null,
            onTouchEnd: null,
            onDestroy: null,
            onZoomStart: null,
            onZoom: null,
            onZoomEnd: null
        };
        for (c in r) i.options[c] = r[c];
        i.x = i.options.x,
        i.y = i.options.y,
        i.options.useTransform = g && i.options.useTransform,
        i.options.hScrollbar = i.options.hScroll && i.options.hScrollbar,
        i.options.vScrollbar = i.options.vScroll && i.options.vScrollbar,
        i.options.zoom = i.options.useTransform && i.options.zoom,
        i.options.useTransition = y && i.options.useTransition,
        i.options.zoom && h && (k = ""),
        i.scroller.style[u] = i.options.useTransform ? s + "transform": "top left",
        i.scroller.style[a] = "0",
        i.scroller.style[f] = "0 0",
        i.options.useTransition && (i.scroller.style[l] = "cubic-bezier(0.33,0.66,0.66,1)"),
        i.options.useTransform ? i.scroller.style[o] = "translate(" + i.x + "px," + i.y + "px)" + k: i.scroller.style.cssText += ";position:absolute;top:" + i.y + "px;left:" + i.x + "px",
        i.options.useTransition && (i.options.fixedScrollbar = !0),
        i.refresh(),
        i._bind(b, e),
        i._bind(w),
        m || i.options.wheelAction != "none" && (i._bind("DOMMouseScroll"), i._bind("mousewheel")),
        i.options.checkDOMChanges && (i.checkDOMTime = setInterval(function() {
            i._checkDOMChanges()
        },
        500))
    };
    L.prototype = {
        enabled: !0,
        x: 0,
        y: 0,
        steps: [],
        scale: 1,
        currPageX: 0,
        currPageY: 0,
        pagesX: [],
        pagesY: [],
        aniTime: null,
        wheelZoomCount: 0,
        handleEvent: function(e) {
            var t = this;
            switch (e.type) {
            case w:
                if (!m && e.button !== 0) return;
                t._start(e);
                break;
            case E:
                t._move(e);
                break;
            case S:
            case x:
                t._end(e);
                break;
            case b:
                t._resize();
                break;
            case "DOMMouseScroll":
            case "mousewheel":
                t._wheel(e);
                break;
            case T:
                t._transitionEnd(e)
            }
        },
        _checkDOMChanges: function() {
            if (this.moved || this.zoomed || this.animating || this.scrollerW == this.scroller.offsetWidth * this.scale && this.scrollerH == this.scroller.offsetHeight * this.scale) return;
            this.refresh()
        },
        _scrollbar: function(e) {
            var r = this,
            i;
            if (!r[e + "Scrollbar"]) {
                r[e + "ScrollbarWrapper"] && (g && (r[e + "ScrollbarIndicator"].style[o] = ""), r[e + "ScrollbarWrapper"].parentNode.removeChild(r[e + "ScrollbarWrapper"]), r[e + "ScrollbarWrapper"] = null, r[e + "ScrollbarIndicator"] = null);
                return
            }
            r[e + "ScrollbarWrapper"] || (i = t.createElement("div"), r.options.scrollbarClass ? i.className = r.options.scrollbarClass + e.toUpperCase() : i.style.cssText = "position:absolute;z-index:100;" + (e == "h" ? "height:7px;bottom:1px;left:2px;right:" + (r.vScrollbar ? "7": "2") + "px": "width:7px;bottom:" + (r.hScrollbar ? "7": "2") + "px;top:2px;right:1px"), i.style.cssText += ";pointer-events:none;" + s + "transition-property:opacity;" + s + "transition-duration:" + (r.options.fadeScrollbar ? "350ms": "0") + ";overflow:hidden;opacity:" + (r.options.hideScrollbar ? "0": "1"), r.wrapper.appendChild(i), r[e + "ScrollbarWrapper"] = i, i = t.createElement("div"), r.options.scrollbarClass || (i.style.cssText = "position:absolute;z-index:100;background:rgba(0,0,0,0.5);border:1px solid rgba(255,255,255,0.9);" + s + "background-clip:padding-box;" + s + "box-sizing:border-box;" + (e == "h" ? "height:100%": "width:100%") + ";" + s + "border-radius:3px;border-radius:3px"), i.style.cssText += ";pointer-events:none;" + s + "transition-property:" + s + "transform;" + s + "transition-timing-function:cubic-bezier(0.33,0.66,0.66,1);" + s + "transition-duration:0;" + s + "transform: translate(0,0)" + k, r.options.useTransition && (i.style.cssText += ";" + s + "transition-timing-function:cubic-bezier(0.33,0.66,0.66,1)"), r[e + "ScrollbarWrapper"].appendChild(i), r[e + "ScrollbarIndicator"] = i),
            e == "h" ? (r.hScrollbarSize = r.hScrollbarWrapper.clientWidth, r.hScrollbarIndicatorSize = n.max(n.round(r.hScrollbarSize * r.hScrollbarSize / r.scrollerW), 8), r.hScrollbarIndicator.style.width = r.hScrollbarIndicatorSize + "px", r.hScrollbarMaxScroll = r.hScrollbarSize - r.hScrollbarIndicatorSize, r.hScrollbarProp = r.hScrollbarMaxScroll / r.maxScrollX) : (r.vScrollbarSize = r.vScrollbarWrapper.clientHeight, r.vScrollbarIndicatorSize = n.max(n.round(r.vScrollbarSize * r.vScrollbarSize / r.scrollerH), 8), r.vScrollbarIndicator.style.height = r.vScrollbarIndicatorSize + "px", r.vScrollbarMaxScroll = r.vScrollbarSize - r.vScrollbarIndicatorSize, r.vScrollbarProp = r.vScrollbarMaxScroll / r.maxScrollY),
            r._scrollbarPos(e, !0)
        },
        _resize: function() {
            var e = this;
            setTimeout(function() {
                e.refresh()
            },
            h ? 200 : 0)
        },
        _pos: function(e, t) {
            if (this.zoomed) return;
            e = this.hScroll ? e: 0,
            t = this.vScroll ? t: 0,
            this.options.useTransform ? this.scroller.style[o] = "translate(" + e + "px," + t + "px) scale(" + this.scale + ")" + k: (e = n.round(e), t = n.round(t), this.scroller.style.left = e + "px", this.scroller.style.top = t + "px"),
            this.x = e,
            this.y = t,
            this._scrollbarPos("h"),
            this._scrollbarPos("v")
        },
        _scrollbarPos: function(e, t) {
            var r = this,
            i = e == "h" ? r.x: r.y,
            s;
            if (!r[e + "Scrollbar"]) return;
            i = r[e + "ScrollbarProp"] * i,
            i < 0 ? (r.options.fixedScrollbar || (s = r[e + "ScrollbarIndicatorSize"] + n.round(i * 3), s < 8 && (s = 8), r[e + "ScrollbarIndicator"].style[e == "h" ? "width": "height"] = s + "px"), i = 0) : i > r[e + "ScrollbarMaxScroll"] && (r.options.fixedScrollbar ? i = r[e + "ScrollbarMaxScroll"] : (s = r[e + "ScrollbarIndicatorSize"] - n.round((i - r[e + "ScrollbarMaxScroll"]) * 3), s < 8 && (s = 8), r[e + "ScrollbarIndicator"].style[e == "h" ? "width": "height"] = s + "px", i = r[e + "ScrollbarMaxScroll"] + (r[e + "ScrollbarIndicatorSize"] - s))),
            r[e + "ScrollbarWrapper"].style[c] = "0",
            r[e + "ScrollbarWrapper"].style.opacity = t && r.options.hideScrollbar ? "0": "1",
            r[e + "ScrollbarIndicator"].style[o] = "translate(" + (e == "h" ? i + "px,0)": "0," + i + "px)") + k
        },
        _start: function(t) {
            var r = this,
            i = m ? t.touches[0] : t,
            s,
            u,
            a,
            f,
            l;
            if (!r.enabled) return;
            r.options.onBeforeScrollStart && r.options.onBeforeScrollStart.call(r, t),
            (r.options.useTransition || r.options.zoom) && r._transitionTime(0),
            r.moved = !1,
            r.animating = !1,
            r.zoomed = !1,
            r.distX = 0,
            r.distY = 0,
            r.absDistX = 0,
            r.absDistY = 0,
            r.dirX = 0,
            r.dirY = 0,
            r.options.zoom && m && t.touches.length > 1 && (f = n.abs(t.touches[0].pageX - t.touches[1].pageX), l = n.abs(t.touches[0].pageY - t.touches[1].pageY), r.touchesDistStart = n.sqrt(f * f + l * l), r.originX = n.abs(t.touches[0].pageX + t.touches[1].pageX - r.wrapperOffsetLeft * 2) / 2 - r.x, r.originY = n.abs(t.touches[0].pageY + t.touches[1].pageY - r.wrapperOffsetTop * 2) / 2 - r.y, r.options.onZoomStart && r.options.onZoomStart.call(r, t));
            if (r.options.momentum) {
                r.options.useTransform ? (s = getComputedStyle(r.scroller, null)[o].replace(/[^0-9\-.,]/g, "").split(","), u = +(s[12] || s[4]), a = +(s[13] || s[5])) : (u = +getComputedStyle(r.scroller, null).left.replace(/[^0-9-]/g, ""), a = +getComputedStyle(r.scroller, null).top.replace(/[^0-9-]/g, ""));
                if (u != r.x || a != r.y) r.options.useTransition ? r._unbind(T) : C(r.aniTime),
                r.steps = [],
                r._pos(u, a),
                r.options.onScrollEnd && r.options.onScrollEnd.call(r)
            }
            r.absStartX = r.x,
            r.absStartY = r.y,
            r.startX = r.x,
            r.startY = r.y,
            r.pointX = i.pageX,
            r.pointY = i.pageY,
            r.startTime = t.timeStamp || Date.now(),
            r.options.onScrollStart && r.options.onScrollStart.call(r, t),
            r._bind(E, e),
            r._bind(S, e),
            r._bind(x, e)
        },
        _move: function(e) {
            var t = this,
            r = m ? e.touches[0] : e,
            i = r.pageX - t.pointX,
            s = r.pageY - t.pointY,
            u = t.x + i,
            a = t.y + s,
            f,
            l,
            c,
            h = e.timeStamp || Date.now();
            t.options.onBeforeScrollMove && t.options.onBeforeScrollMove.call(t, e);
            if (t.options.zoom && m && e.touches.length > 1) {
                f = n.abs(e.touches[0].pageX - e.touches[1].pageX),
                l = n.abs(e.touches[0].pageY - e.touches[1].pageY),
                t.touchesDist = n.sqrt(f * f + l * l),
                t.zoomed = !0,
                c = 1 / t.touchesDistStart * t.touchesDist * this.scale,
                c < t.options.zoomMin ? c = .5 * t.options.zoomMin * Math.pow(2, c / t.options.zoomMin) : c > t.options.zoomMax && (c = 2 * t.options.zoomMax * Math.pow(.5, t.options.zoomMax / c)),
                t.lastScale = c / this.scale,
                u = this.originX - this.originX * t.lastScale + this.x,
                a = this.originY - this.originY * t.lastScale + this.y,
                this.scroller.style[o] = "translate(" + u + "px," + a + "px) scale(" + c + ")" + k,
                t.options.onZoom && t.options.onZoom.call(t, e);
                return
            }
            t.pointX = r.pageX,
            t.pointY = r.pageY;
            if (u > 0 || u < t.maxScrollX) u = t.options.bounce ? t.x + i / 2 : u >= 0 || t.maxScrollX >= 0 ? 0 : t.maxScrollX;
            if (a > t.minScrollY || a < t.maxScrollY) a = t.options.bounce ? t.y + s / 2 : a >= t.minScrollY || t.maxScrollY >= 0 ? t.minScrollY: t.maxScrollY;
            t.distX += i,
            t.distY += s,
            t.absDistX = n.abs(t.distX),
            t.absDistY = n.abs(t.distY);
            if (t.absDistX < 6 && t.absDistY < 6) return;
            t.options.lockDirection && (t.absDistX > t.absDistY + 5 ? (a = t.y, s = 0) : t.absDistY > t.absDistX + 5 && (u = t.x, i = 0)),
            t.moved = !0,
            t._pos(u, a),
            t.dirX = i > 0 ? -1 : i < 0 ? 1 : 0,
            t.dirY = s > 0 ? -1 : s < 0 ? 1 : 0,
            h - t.startTime > 300 && (t.startTime = h, t.startX = t.x, t.startY = t.y),
            t.options.onScrollMove && t.options.onScrollMove.call(t, e)
        },
        _end: function(r) {
            if (m && r.touches.length !== 0) return;
            var i = this,
            s = m ? r.changedTouches[0] : r,
            u,
            f,
            l = {
                dist: 0,
                time: 0
            },
            c = {
                dist: 0,
                time: 0
            },
            h = (r.timeStamp || Date.now()) - i.startTime,
            p = i.x,
            d = i.y,
            v,
            g,
            y,
            b,
            w;
            i._unbind(E, e),
            i._unbind(S, e),
            i._unbind(x, e),
            i.options.onBeforeScrollEnd && i.options.onBeforeScrollEnd.call(i, r);
            if (i.zoomed) {
                w = i.scale * i.lastScale,
                w = Math.max(i.options.zoomMin, w),
                w = Math.min(i.options.zoomMax, w),
                i.lastScale = w / i.scale,
                i.scale = w,
                i.x = i.originX - i.originX * i.lastScale + i.x,
                i.y = i.originY - i.originY * i.lastScale + i.y,
                i.scroller.style[a] = "200ms",
                i.scroller.style[o] = "translate(" + i.x + "px," + i.y + "px) scale(" + i.scale + ")" + k,
                i.zoomed = !1,
                i.refresh(),
                i.options.onZoomEnd && i.options.onZoomEnd.call(i, r);
                return
            }
            if (!i.moved) {
                m && (i.doubleTapTimer && i.options.zoom ? (clearTimeout(i.doubleTapTimer), i.doubleTapTimer = null, i.options.onZoomStart && i.options.onZoomStart.call(i, r), i.zoom(i.pointX, i.pointY, i.scale == 1 ? i.options.doubleTapZoom: 1), i.options.onZoomEnd && setTimeout(function() {
                    i.options.onZoomEnd.call(i, r)
                },
                200)) : this.options.handleClick && (i.doubleTapTimer = setTimeout(function() {
                    i.doubleTapTimer = null,
                    u = s.target;
                    while (u.nodeType != 1) u = u.parentNode;
                    u.tagName != "SELECT" && u.tagName != "INPUT" && u.tagName != "TEXTAREA" && (f = t.createEvent("MouseEvents"), f.initMouseEvent("click", !0, !0, r.view, 1, s.screenX, s.screenY, s.clientX, s.clientY, r.ctrlKey, r.altKey, r.shiftKey, r.metaKey, 0, null), f._fake = !0, u.dispatchEvent(f))
                },
                i.options.zoom ? 250 : 0))),
                i._resetPos(400),
                i.options.onTouchEnd && i.options.onTouchEnd.call(i, r);
                return
            }
            if (h < 300 && i.options.momentum) {
                l = p ? i._momentum(p - i.startX, h, -i.x, i.scrollerW - i.wrapperW + i.x, i.options.bounce ? i.wrapperW: 0) : l,
                c = d ? i._momentum(d - i.startY, h, -i.y, i.maxScrollY < 0 ? i.scrollerH - i.wrapperH + i.y - i.minScrollY: 0, i.options.bounce ? i.wrapperH: 0) : c,
                p = i.x + l.dist,
                d = i.y + c.dist;
                if (i.x > 0 && p > 0 || i.x < i.maxScrollX && p < i.maxScrollX) l = {
                    dist: 0,
                    time: 0
                };
                if (i.y > i.minScrollY && d > i.minScrollY || i.y < i.maxScrollY && d < i.maxScrollY) c = {
                    dist: 0,
                    time: 0
                }
            }
            if (l.dist || c.dist) {
                y = n.max(n.max(l.time, c.time), 10),
                i.options.snap && (v = p - i.absStartX, g = d - i.absStartY, n.abs(v) < i.options.snapThreshold && n.abs(g) < i.options.snapThreshold ? i.scrollTo(i.absStartX, i.absStartY, 200) : (b = i._snap(p, d), p = b.x, d = b.y, y = n.max(b.time, y))),
                i.scrollTo(n.round(p), n.round(d), y),
                i.options.onTouchEnd && i.options.onTouchEnd.call(i, r);
                return
            }
            if (i.options.snap) {
                v = p - i.absStartX,
                g = d - i.absStartY,
                n.abs(v) < i.options.snapThreshold && n.abs(g) < i.options.snapThreshold ? i.scrollTo(i.absStartX, i.absStartY, 200) : (b = i._snap(i.x, i.y), (b.x != i.x || b.y != i.y) && i.scrollTo(b.x, b.y, b.time)),
                i.options.onTouchEnd && i.options.onTouchEnd.call(i, r);
                return
            }
            i._resetPos(200),
            i.options.onTouchEnd && i.options.onTouchEnd.call(i, r)
        },
        _resetPos: function(e) {
            var t = this,
            n = t.x >= 0 ? 0 : t.x < t.maxScrollX ? t.maxScrollX: t.x,
            r = t.y >= t.minScrollY || t.maxScrollY > 0 ? t.minScrollY: t.y < t.maxScrollY ? t.maxScrollY: t.y;
            if (n == t.x && r == t.y) {
                t.moved && (t.moved = !1, t.options.onScrollEnd && t.options.onScrollEnd.call(t)),
                t.hScrollbar && t.options.hideScrollbar && (i == "webkit" && (t.hScrollbarWrapper.style[c] = "300ms"), t.hScrollbarWrapper.style.opacity = "0"),
                t.vScrollbar && t.options.hideScrollbar && (i == "webkit" && (t.vScrollbarWrapper.style[c] = "300ms"), t.vScrollbarWrapper.style.opacity = "0");
                return
            }
            t.scrollTo(n, r, e || 0)
        },
        _wheel: function(e) {
            var t = this,
            n, r, i, s, o;
            if ("wheelDeltaX" in e) n = e.wheelDeltaX / 12,
            r = e.wheelDeltaY / 12;
            else if ("wheelDelta" in e) n = r = e.wheelDelta / 12;
            else {
                if (! ("detail" in e)) return;
                n = r = -e.detail * 3
            }
            if (t.options.wheelAction == "zoom") {
                o = t.scale * Math.pow(2, 1 / 3 * (r ? r / Math.abs(r) : 0)),
                o < t.options.zoomMin && (o = t.options.zoomMin),
                o > t.options.zoomMax && (o = t.options.zoomMax),
                o != t.scale && (!t.wheelZoomCount && t.options.onZoomStart && t.options.onZoomStart.call(t, e), t.wheelZoomCount++, t.zoom(e.pageX, e.pageY, o, 400), setTimeout(function() {
                    t.wheelZoomCount--,
                    !t.wheelZoomCount && t.options.onZoomEnd && t.options.onZoomEnd.call(t, e)
                },
                400));
                return
            }
            i = t.x + n,
            s = t.y + r,
            i > 0 ? i = 0 : i < t.maxScrollX && (i = t.maxScrollX),
            s > t.minScrollY ? s = t.minScrollY: s < t.maxScrollY && (s = t.maxScrollY),
            t.maxScrollY < 0 && t.scrollTo(i, s, 0)
        },
        _transitionEnd: function(e) {
            var t = this;
            if (e.target != t.scroller) return;
            t._unbind(T),
            t._startAni()
        },
        _startAni: function() {
            var e = this,
            t = e.x,
            r = e.y,
            i = Date.now(),
            s,
            o,
            u;
            if (e.animating) return;
            if (!e.steps.length) {
                e._resetPos(400);
                return
            }
            s = e.steps.shift(),
            s.x == t && s.y == r && (s.time = 0),
            e.animating = !0,
            e.moved = !0;
            if (e.options.useTransition) {
                e._transitionTime(s.time),
                e._pos(s.x, s.y),
                e.animating = !1,
                s.time ? e._bind(T) : e._resetPos(0);
                return
            }
            u = function() {
                var a = Date.now(),
                f,
                l;
                if (a >= i + s.time) {
                    e._pos(s.x, s.y),
                    e.animating = !1,
                    e.options.onAnimationEnd && e.options.onAnimationEnd.call(e),
                    e._startAni();
                    return
                }
                a = (a - i) / s.time - 1,
                o = n.sqrt(1 - a * a),
                f = (s.x - t) * o + t,
                l = (s.y - r) * o + r,
                e._pos(f, l),
                e.animating && (e.aniTime = N(u))
            },
            u()
        },
        _transitionTime: function(e) {
            e += "ms",
            this.scroller.style[a] = e,
            this.hScrollbar && (this.hScrollbarIndicator.style[a] = e),
            this.vScrollbar && (this.vScrollbarIndicator.style[a] = e)
        },
        _momentum: function(e, t, r, i, s) {
            var o = 6e-4,
            u = n.abs(e) / t,
            a = u * u / (2 * o),
            f = 0,
            l = 0;
            return e > 0 && a > r ? (l = s / (6 / (a / u * o)), r += l, u = u * r / a, a = r) : e < 0 && a > i && (l = s / (6 / (a / u * o)), i += l, u = u * i / a, a = i),
            a *= e < 0 ? -1 : 1,
            f = u / o,
            {
                dist: a,
                time: n.round(f)
            }
        },
        _offset: function(e) {
            var t = -e.offsetLeft,
            n = -e.offsetTop;
            while (e = e.offsetParent) t -= e.offsetLeft,
            n -= e.offsetTop;
            return e != this.wrapper && (t *= this.scale, n *= this.scale),
            {
                left: t,
                top: n
            }
        },
        _snap: function(e, t) {
            var r = this,
            i, s, o, u, a, f;
            o = r.pagesX.length - 1;
            for (i = 0, s = r.pagesX.length; i < s; i++) if (e >= r.pagesX[i]) {
                o = i;
                break
            }
            o == r.currPageX && o > 0 && r.dirX < 0 && o--,
            e = r.pagesX[o],
            a = n.abs(e - r.pagesX[r.currPageX]),
            a = a ? n.abs(r.x - e) / a * 500 : 0,
            r.currPageX = o,
            o = r.pagesY.length - 1;
            for (i = 0; i < o; i++) if (t >= r.pagesY[i]) {
                o = i;
                break
            }
            return o == r.currPageY && o > 0 && r.dirY < 0 && o--,
            t = r.pagesY[o],
            f = n.abs(t - r.pagesY[r.currPageY]),
            f = f ? n.abs(r.y - t) / f * 500 : 0,
            r.currPageY = o,
            u = n.round(n.max(a, f)) || 200,
            {
                x: e,
                y: t,
                time: u
            }
        },
        _bind: function(e, t, n) { (t || this.scroller).addEventListener(e, this, !!n)
        },
        _unbind: function(e, t, n) { (t || this.scroller).removeEventListener(e, this, !!n)
        },
        destroy: function() {
            var t = this;
            t.scroller.style[o] = "",
            t.hScrollbar = !1,
            t.vScrollbar = !1,
            t._scrollbar("h"),
            t._scrollbar("v"),
            t._unbind(b, e),
            t._unbind(w),
            t._unbind(E, e),
            t._unbind(S, e),
            t._unbind(x, e),
            t.options.hasTouch || (t._unbind("DOMMouseScroll"), t._unbind("mousewheel")),
            t.options.useTransition && t._unbind(T),
            t.options.checkDOMChanges && clearInterval(t.checkDOMTime),
            t.options.onDestroy && t.options.onDestroy.call(t)
        },
        refresh: function() {
            var e = this,
            t, r, i, s, o = 0,
            u = 0;
            e.scale < e.options.zoomMin && (e.scale = e.options.zoomMin),
            e.wrapperW = e.wrapper.clientWidth || 1,
            e.wrapperH = e.wrapper.clientHeight || 1,
            e.minScrollY = -e.options.topOffset || 0,
            e.scrollerW = n.round(e.scroller.offsetWidth * e.scale),
            e.scrollerH = n.round((e.scroller.offsetHeight + e.minScrollY) * e.scale),
            e.maxScrollX = e.wrapperW - e.scrollerW,
            e.maxScrollY = e.wrapperH - e.scrollerH + e.minScrollY,
            e.dirX = 0,
            e.dirY = 0,
            e.options.onRefresh && e.options.onRefresh.call(e),
            e.hScroll = e.options.hScroll && e.maxScrollX < 0,
            e.vScroll = e.options.vScroll && (!e.options.bounceLock && !e.hScroll || e.scrollerH > e.wrapperH),
            e.hScrollbar = e.hScroll && e.options.hScrollbar,
            e.vScrollbar = e.vScroll && e.options.vScrollbar && e.scrollerH > e.wrapperH,
            t = e._offset(e.wrapper),
            e.wrapperOffsetLeft = -t.left,
            e.wrapperOffsetTop = -t.top;
            if (typeof e.options.snap == "string") {
                e.pagesX = [],
                e.pagesY = [],
                s = e.scroller.querySelectorAll(e.options.snap);
                for (r = 0, i = s.length; r < i; r++) o = e._offset(s[r]),
                o.left += e.wrapperOffsetLeft,
                o.top += e.wrapperOffsetTop,
                e.pagesX[r] = o.left < e.maxScrollX ? e.maxScrollX: o.left * e.scale,
                e.pagesY[r] = o.top < e.maxScrollY ? e.maxScrollY: o.top * e.scale
            } else if (e.options.snap) {
                e.pagesX = [];
                while (o >= e.maxScrollX) e.pagesX[u] = o,
                o -= e.wrapperW,
                u++;
                e.maxScrollX % e.wrapperW && (e.pagesX[e.pagesX.length] = e.maxScrollX - e.pagesX[e.pagesX.length - 1] + e.pagesX[e.pagesX.length - 1]),
                o = 0,
                u = 0,
                e.pagesY = [];
                while (o >= e.maxScrollY) e.pagesY[u] = o,
                o -= e.wrapperH,
                u++;
                e.maxScrollY % e.wrapperH && (e.pagesY[e.pagesY.length] = e.maxScrollY - e.pagesY[e.pagesY.length - 1] + e.pagesY[e.pagesY.length - 1])
            }
            e._scrollbar("h"),
            e._scrollbar("v"),
            e.zoomed || (e.scroller.style[a] = "0", e._resetPos(400))
        },
        scrollTo: function(e, t, n, r) {
            var i = this,
            s = e,
            o, u;
            i.stop(),
            s.length || (s = [{
                x: e,
                y: t,
                time: n,
                relative: r
            }]);
            for (o = 0, u = s.length; o < u; o++) s[o].relative && (s[o].x = i.x - s[o].x, s[o].y = i.y - s[o].y),
            i.steps.push({
                x: s[o].x,
                y: s[o].y,
                time: s[o].time || 0
            });
            i._startAni()
        },
        scrollToElement: function(e, t) {
            var r = this,
            i;
            e = e.nodeType ? e: r.scroller.querySelector(e);
            if (!e) return;
            i = r._offset(e),
            i.left += r.wrapperOffsetLeft,
            i.top += r.wrapperOffsetTop,
            i.left = i.left > 0 ? 0 : i.left < r.maxScrollX ? r.maxScrollX: i.left,
            i.top = i.top > r.minScrollY ? r.minScrollY: i.top < r.maxScrollY ? r.maxScrollY: i.top,
            t = t === undefined ? n.max(n.abs(i.left) * 2, n.abs(i.top) * 2) : t,
            r.scrollTo(i.left, i.top, t)
        },
        scrollToPage: function(e, t, n) {
            var r = this,
            i, s;
            n = n === undefined ? 400 : n,
            r.options.onScrollStart && r.options.onScrollStart.call(r),
            r.options.snap ? (e = e == "next" ? r.currPageX + 1 : e == "prev" ? r.currPageX - 1 : e, t = t == "next" ? r.currPageY + 1 : t == "prev" ? r.currPageY - 1 : t, e = e < 0 ? 0 : e > r.pagesX.length - 1 ? r.pagesX.length - 1 : e, t = t < 0 ? 0 : t > r.pagesY.length - 1 ? r.pagesY.length - 1 : t, r.currPageX = e, r.currPageY = t, i = r.pagesX[e], s = r.pagesY[t]) : (i = -r.wrapperW * e, s = -r.wrapperH * t, i < r.maxScrollX && (i = r.maxScrollX), s < r.maxScrollY && (s = r.maxScrollY)),
            r.scrollTo(i, s, n)
        },
        disable: function() {
            this.stop(),
            this._resetPos(0),
            this.enabled = !1,
            this._unbind(E, e),
            this._unbind(S, e),
            this._unbind(x, e)
        },
        enable: function() {
            this.enabled = !0
        },
        stop: function() {
            this.options.useTransition ? this._unbind(T) : C(this.aniTime),
            this.steps = [],
            this.moved = !1,
            this.animating = !1
        },
        zoom: function(e, t, n, r) {
            var i = this,
            s = n / i.scale;
            if (!i.options.useTransform) return;
            i.zoomed = !0,
            r = r === undefined ? 200 : r,
            e = e - i.wrapperOffsetLeft - i.x,
            t = t - i.wrapperOffsetTop - i.y,
            i.x = e - e * s + i.x,
            i.y = t - t * s + i.y,
            i.scale = n,
            i.refresh(),
            i.x = i.x > 0 ? 0 : i.x < i.maxScrollX ? i.maxScrollX: i.x,
            i.y = i.y > i.minScrollY ? i.minScrollY: i.y < i.maxScrollY ? i.maxScrollY: i.y,
            i.scroller.style[a] = r + "ms",
            i.scroller.style[o] = "translate(" + i.x + "px," + i.y + "px) scale(" + n + ")" + k,
            i.zoomed = !1
        },
        isReady: function() {
            return ! this.moved && !this.zoomed && !this.animating
        }
    },
    r = null,
    typeof exports != "undefined" ? exports.iScroll = L: e.iScroll = L
})(window, document); (function(e) {
    var t = e.qh360cp || {};
    t.template = function(t, r) {
        var i = function() {
            if (!e.document) return n._compile(t);
            var r = document.getElementById(t);
            if (r) {
                if (n.cache[t]) return n.cache[t];
                var i = /^(textarea|input)$/i.test(r.nodeName) ? r.value: r.innerHTML;
                return n._compile(i)
            }
            return n._compile(t)
        } (),
        s = n._isObject(r) ? i(r) : i;
        return i = null,
        s
    };
    var n = t.template;
    n.versions = n.versions || [],
    n.versions.push("1.0.6"),
    n.cache = {},
    n.LEFT_DELIMITER = n.LEFT_DELIMITER || "<%",
    n.RIGHT_DELIMITER = n.RIGHT_DELIMITER || "%>",
    n.ESCAPE = !0,
    n._encodeHTML = function(e) {
        return String(e).replace(/&/g, "&amp;").replace(/</g, "&lt;").replace(/>/g, "&gt;").replace(/\\/g, "&#92;").replace(/"/g, "&quot;").replace(/'/g, "&#39;")
    },
    n._encodeReg = function(e) {
        return String(e);
    },
    n._encodeEventHTML = function(e) {
        return String(e).replace(/&/g, "&amp;").replace(/</g, "&lt;").replace(/>/g, "&gt;").replace(/"/g, "&quot;").replace(/'/g, "&#39;").replace(/\\\\/g, "\\").replace(/\\\//g, "/").replace(/\\n/g, "\n").replace(/\\r/g, "\r")
    },
    n._compile = function(e) {
        var t = "var _template_fun_array=[];\nvar fn=(function(__data__){\nvar _template_varName='';\nfor(name in __data__){\n_template_varName+=('var '+name+'=__data__[\"'+name+'\"];');\n};\neval(_template_varName);\n_template_fun_array.push('" + n._analysisStr(e) + "');\n_template_varName=null;\n})(_template_object);\nfn = null;\nreturn _template_fun_array.join('');\n";
        return new Function("_template_object", t)
    },
    n._isObject = function(e) {
        return "function" == typeof e || !!e && "object" == typeof e
    },
    n._analysisStr = function(e) {
        var t = n.LEFT_DELIMITER,
        r = n.RIGHT_DELIMITER,
        i = n._encodeReg(t),
        s = n._encodeReg(r);
        return e = String(e).replace(new RegExp("(" + i + "[^" + s + "]*)//.*\n", "g"), "$1").replace(new RegExp("<!--.*?-->", "g"), "").replace(new RegExp(i + "\\*.*?\\*" + s, "g"), "").replace(new RegExp("[\\r\\t\\n]", "g"), "").replace(new RegExp(i + "(?:(?!" + s + ")[\\s\\S])*" + s + "|((?:(?!" + i + ")[\\s\\S])+)", "g"),
        function(e, t) {
            var n = "";
            if (t) {
                n = t.replace(/\\/g, "&#92;").replace(/'/g, "&#39;");
                while (/<[^<]*?&#39;[^<]*?>/g.test(n)) n = n.replace(/(<[^<]*?)&#39;([^<]*?>)/g, "$1\r$2")
            } else n = e;
            return n
        }),
        e = e.replace(new RegExp("(" + i + "[\\s]*?var[\\s]*?.*?[\\s]*?[^;])[\\s]*?" + s, "g"), "$1;" + r).replace(new RegExp("(" + i + ":?[hvu]?[\\s]*?=[\\s]*?[^;|" + s + "]*?);[\\s]*?" + s, "g"), "$1" + r).split(t).join("	"),
        n.ESCAPE ? e = e.replace(new RegExp("\\t=(.*?)" + s, "g"), "',typeof($1) === 'undefined'?'':Q.template._encodeHTML($1),'") : e = e.replace(new RegExp("\\t=(.*?)" + s, "g"), "',typeof($1) === 'undefined'?'':$1,'"),
        e = e.replace(new RegExp("\\t:h=(.*?)" + s, "g"), "',typeof($1) === 'undefined'?'':Q.template._encodeHTML($1),'").replace(new RegExp("\\t(?::=|-)(.*?)" + s, "g"), "',typeof($1)==='undefined'?'':$1,'").replace(new RegExp("\\t:u=(.*?)" + s, "g"), "',typeof($1)==='undefined'?'':encodeURIComponent($1),'").replace(new RegExp("\\t:v=(.*?)" + s, "g"), "',typeof($1)==='undefined'?'':Q.template._encodeEventHTML($1),'").split("	").join("');").split(r).join("_template_fun_array.push('").split("\r").join("\\'"),
        e
    }
})(window);