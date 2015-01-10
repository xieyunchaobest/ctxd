
/* IE7/IE8.js - copyright 2004-2008, Dean Edwards */
(function () {
	IE7 = {toString:function () {
		return "IE7 version 2.0 (beta3)";
	}};
	var m = IE7.appVersion = navigator.appVersion.match(/MSIE (\d\.\d)/)[1];
	if (/ie7_off/.test(top.location.search) || m < 5) {
		return;
	}
	var U = bT();
	var G = document.compatMode != "CSS1Compat";
	var bx = document.documentElement, w, t;
	var bN = "!";
	var J = ":link{ie7-link:link}:visited{ie7-link:visited}";
	var cB = /^[\w\.]+[^:]*$/;
	function bc(a, b) {
		if (cB.test(a)) {
			a = (b || "") + a;
		}
		return a;
	}
	function by(a, b) {
		a = bc(a, b);
		return a.slice(0, a.lastIndexOf("/") + 1);
	}
	var bO = document.scripts[document.scripts.length - 1];
	var cC = by(bO.src);
	try {
		var K = new ActiveXObject("Microsoft.XMLHTTP");
	}
	catch (e) {
	}
	var bd = {};
	function cD(a, b) {
		try {
			a = bc(a, b);
			if (!bd[a]) {
				K.open("GET", a, false);
				K.send();
				if (K.status == 0 || K.status == 200) {
					bd[a] = K.responseText;
				}
			}
		}
		catch (e) {
		}
		finally {
			return bd[a] || "";
		}
	}
	if (m < 5.5) {
		undefined = U();
		bN = "HTML:!";
		var cE = /(g|gi)$/;
		var cF = String.prototype.replace;
		String.prototype.replace = function (a, b) {
			if (typeof b == "function") {
				if (a && a.constructor == RegExp) {
					var c = a;
					var d = c.global;
					if (d == null) {
						d = cE.test(c);
					}
					if (d) {
						c = new RegExp(c.source);
					}
				} else {
					c = new RegExp(W(a));
				}
				var f, g = this, h = "";
				while (g && (f = c.exec(g))) {
					h += g.slice(0, f.index) + b.apply(this, f);
					g = g.slice(f.index + f[0].length);
					if (!d) {
						break;
					}
				}
				return h + g;
			}
			return cF.apply(this, arguments);
		};
		Array.prototype.pop = function () {
			if (this.length) {
				var a = this[this.length - 1];
				this.length--;
				return a;
			}
			return undefined;
		};
		Array.prototype.push = function () {
			for (var a = 0; a < arguments.length; a++) {
				this[this.length] = arguments[a];
			}
			return this.length;
		};
		var cG = this;
		Function.prototype.apply = function (a, b) {
			if (a === undefined) {
				a = cG;
			} else {
				if (a == null) {
					a = window;
				} else {
					if (typeof a == "string") {
						a = new String(a);
					} else {
						if (typeof a == "number") {
							a = new Number(a);
						} else {
							if (typeof a == "boolean") {
								a = new Boolean(a);
							}
						}
					}
				}
			}
			if (arguments.length == 1) {
				b = [];
			} else {
				if (b[0] && b[0].writeln) {
					b[0] = b[0].documentElement.document || b[0];
				}
			}
			var c = "#ie7_apply", d;
			a[c] = this;
			switch (b.length) {
			  case 0:
				d = a[c]();
				break;
			  case 1:
				d = a[c](b[0]);
				break;
			  case 2:
				d = a[c](b[0], b[1]);
				break;
			  case 3:
				d = a[c](b[0], b[1], b[2]);
				break;
			  case 4:
				d = a[c](b[0], b[1], b[2], b[3]);
				break;
			  case 5:
				d = a[c](b[0], b[1], b[2], b[3], b[4]);
				break;
			  default:
				var f = [], g = b.length - 1;
				do {
					f[g] = "a[" + g + "]";
				} while (g--);
				eval("r=o[$](" + f + ")");
			}
			if (typeof a.valueOf == "function") {
				delete a[c];
			} else {
				a[c] = undefined;
				if (d && d.writeln) {
					d = d.documentElement.document || d;
				}
			}
			return d;
		};
		Function.prototype.call = function (a) {
			return this.apply(a, bP.apply(arguments, [1]));
		};
		J += "address,blockquote,body,dd,div,dt,fieldset,form," + "frame,frameset,h1,h2,h3,h4,h5,h6,iframe,noframes,object,p," + "hr,applet,center,dir,menu,pre,dl,li,ol,ul{display:block}";
	}
	var bP = Array.prototype.slice;
	var cZ = /%([1-9])/g;
	var cH = /^\s\s*/;
	var cI = /\s\s*$/;
	var cJ = /([\/()[\]{}|*+-.,^$?\\])/g;
	var bQ = /\bbase\b/;
	var bR = ["constructor", "toString"];
	var be;
	function B() {
	}
	B.extend = function (a, b) {
		be = true;
		var c = new this;
		bf(c, a);
		be = false;
		var d = c.constructor;
		function f() {
			if (!be) {
				d.apply(this, arguments);
			}
		}
		c.constructor = f;
		f.extend = arguments.callee;
		bf(f, b);
		f.prototype = c;
		return f;
	};
	B.prototype.extend = function (a) {
		return bf(this, a);
	};
	var bz = "#";
	var V = "~";
	var cK = /\\./g;
	var cL = /\(\?[:=!]|\[[^\]]+\]/g;
	var cM = /\(/g;
	var H = B.extend({constructor:function (a) {
		this[V] = [];
		this.merge(a);
	}, exec:function (g) {
		var h = this, j = this[V];
		return String(g).replace(new RegExp(this, this.ignoreCase ? "gi" : "g"), function () {
			var a, b = 1, c = 0;
			while ((a = h[bz + j[c++]])) {
				var d = b + a.length + 1;
				if (arguments[b]) {
					var f = a.replacement;
					switch (typeof f) {
					  case "function":
						return f.apply(h, bP.call(arguments, b, d));
					  case "number":
						return arguments[b + f];
					  default:
						return f;
					}
				}
				b = d;
			}
		});
	}, add:function (a, b) {
		if (a instanceof RegExp) {
			a = a.source;
		}
		if (!this[bz + a]) {
			this[V].push(String(a));
		}
		this[bz + a] = new H.Item(a, b);
	}, merge:function (a) {
		for (var b in a) {
			this.add(b, a[b]);
		}
	}, toString:function () {
		return "(" + this[V].join(")|(") + ")";
	}}, {IGNORE:"$0", Item:B.extend({constructor:function (a, b) {
		a = a instanceof RegExp ? a.source : String(a);
		if (typeof b == "number") {
			b = String(b);
		} else {
			if (b == null) {
				b = "";
			}
		}
		if (typeof b == "string" && /\$(\d+)/.test(b)) {
			if (/^\$\d+$/.test(b)) {
				b = parseInt(b.slice(1));
			} else {
				var c = /'/.test(b.replace(/\\./g, "")) ? "\"" : "'";
				b = b.replace(/\n/g, "\\n").replace(/\r/g, "\\r").replace(/\$(\d+)/g, c + "+(arguments[$1]||" + c + c + ")+" + c);
				b = new Function("return " + c + b.replace(/(['"])\1\+(.*)\+\1\1$/, "$1") + c);
			}
		}
		this.length = H.count(a);
		this.replacement = b;
		this.toString = bT(a);
	}}), count:function (a) {
		a = String(a).replace(cK, "").replace(cL, "");
		return L(a, cM).length;
	}});
	function bf(a, b) {
		if (a && b) {
			var c = (typeof b == "function" ? Function : Object).prototype;
			var d = bR.length, f;
			if (be) {
				while (f = bR[--d]) {
					var g = b[f];
					if (g != c[f]) {
						if (bQ.test(g)) {
							bS(a, f, g);
						} else {
							a[f] = g;
						}
					}
				}
			}
			for (f in b) {
				if (c[f] === undefined) {
					var g = b[f];
					if (a[f] && typeof g == "function" && bQ.test(g)) {
						bS(a, f, g);
					} else {
						a[f] = g;
					}
				}
			}
		}
		return a;
	}
	function bS(c, d, f) {
		var g = c[d];
		c[d] = function () {
			var a = this.base;
			this.base = g;
			var b = f.apply(this, arguments);
			this.base = a;
			return b;
		};
	}
	function cN(a, b) {
		if (!b) {
			b = a;
		}
		var c = {};
		for (var d in a) {
			c[d] = b[d];
		}
		return c;
	}
	function i(c) {
		var d = arguments;
		var f = new RegExp("%([1-" + arguments.length + "])", "g");
		return String(c).replace(f, function (a, b) {
			return b < d.length ? d[b] : a;
		});
	}
	function L(a, b) {
		return String(a).match(b) || [];
	}
	function W(a) {
		return String(a).replace(cJ, "\\$1");
	}
	function da(a) {
		return String(a).replace(cH, "").replace(cI, "");
	}
	function bT(a) {
		return function () {
			return a;
		};
	}
	var bU = H.extend({ignoreCase:true});
	var cO = /\x01(\d+)/g, cP = /'/g, cQ = /^\x01/, cR = /\\([\da-fA-F]{1,4})/g;
	var bA = [];
	var bV = new bU({"<!\\-\\-|\\-\\->":"", "\\/\\*[^*]*\\*+([^\\/][^*]*\\*+)*\\/":"", "@(namespace|import)[^;\\n]+[;\\n]":"", "'(\\\\.|[^'\\\\])*'":bW, "\"(\\\\.|[^\"\\\\])*\"":bW, "\\s+":" "});
	function cS(a) {
		return bV.exec(a);
	}
	function bg(c) {
		return c.replace(cO, function (a, b) {
			return bA[b - 1];
		});
	}
	function bW(c) {
		return "\x01" + bA.push(c.replace(cR, function (a, b) {
			return eval("'\\u" + "0000".slice(b.length) + b + "'");
		}).slice(1, -1).replace(cP, "\\'"));
	}
	function bB(a) {
		return cQ.test(a) ? bA[a.slice(1) - 1] : a;
	}
	var cT = new H({Width:"Height", width:"height", Left:"Top", left:"top", Right:"Bottom", right:"bottom", onX:"onY"});
	function C(a) {
		return cT.exec(a);
	}
	var bX = [];
	function bC(a) {
		cV(a);
		v(window, "onresize", a);
	}
	function v(a, b, c) {
		a.attachEvent(b, c);
		bX.push(arguments);
	}
	function cU(a, b, c) {
		try {
			a.detachEvent(b, c);
		}
		catch (ignore) {
		}
	}
	v(window, "onunload", function () {
		var a;
		while (a = bX.pop()) {
			cU(a[0], a[1], a[2]);
		}
	});
	function X(a, b, c) {
		if (!a.elements) {
			a.elements = {};
		}
		if (c) {
			a.elements[b.uniqueID] = b;
		} else {
			delete a.elements[b.uniqueID];
		}
		return c;
	}
	v(window, "onbeforeprint", function () {
		if (!IE7.CSS.print) {
			new bJ("print");
		}
		IE7.CSS.print.recalc();
	});
	var bY = /^\d+(px)?$/i;
	var M = /^\d+%$/;
	var D = function (a, b) {
		if (bY.test(b)) {
			return parseInt(b);
		}
		var c = a.style.left;
		var d = a.runtimeStyle.left;
		a.runtimeStyle.left = a.currentStyle.left;
		a.style.left = b || 0;
		b = a.style.pixelLeft;
		a.style.left = c;
		a.runtimeStyle.left = d;
		return b;
	};
	var bD = "ie7-";
	var bZ = B.extend({constructor:function () {
		this.fixes = [];
		this.recalcs = [];
	}, init:U});
	var bE = [];
	function cV(a) {
		bE.push(a);
	}
	IE7.recalc = function () {
		IE7.HTML.recalc();
		IE7.CSS.recalc();
		for (var a = 0; a < bE.length; a++) {
			bE[a]();
		}
	};
	function bh(a) {
		return a.currentStyle["ie7-position"] == "fixed";
	}
	function bF(a, b) {
		return a.currentStyle[bD + b] || a.currentStyle[b];
	}
	function N(a, b, c) {
		if (a.currentStyle[bD + b] == null) {
			a.runtimeStyle[bD + b] = a.currentStyle[b];
		}
		a.runtimeStyle[b] = c;
	}
	function ca(a) {
		var b = document.createElement(a || "object");
		b.style.cssText = "position:absolute;padding:0;display:block;border:none;clip:rect(0 0 0 0);left:-9999";
		b.ie7_anon = true;
		return b;
	}
	function x(a, b, c) {
		if (!bj[a]) {
			I = [];
			var d = "";
			var f = E.escape(a).split(",");
			for (var g = 0; g < f.length; g++) {
				p = l = y = 0;
				Y = f.length > 1 ? 2 : 0;
				var h = E.exec(f[g]) || "if(0){";
				if (p) {
					h += i("if(e%1.nodeName!='!'){", l);
				}
				var j = Y > 1 ? ch : "";
				h += i(j + ci, l);
				h += Array(L(h, /\{/g).length + 1).join("}");
				d += h;
			}
			eval(i(cj, I) + E.unescape(d) + "return s?null:r}");
			bj[a] = _k;
		}
		return bj[a](b || document, c);
	}
	var bi = m < 6;
	var cb = /^(href|src)$/;
	var bG = {"class":"className", "for":"htmlFor"};
	IE7._1 = 1;
	IE7._e = function (a, b) {
		var c = a.all[b] || null;
		if (!c || c.id == b) {
			return c;
		}
		for (var d = 0; d < c.length; d++) {
			if (c[d].id == b) {
				return c[d];
			}
		}
		return null;
	};
	IE7._f = function (a, b) {
		if (b == "src" && a.pngSrc) {
			return a.pngSrc;
		}
		var c = bi ? (a.attributes[b] || a.attributes[bG[b.toLowerCase()]]) : a.getAttributeNode(b);
		if (c && (c.specified || b == "value")) {
			if (cb.test(b)) {
				return a.getAttribute(b, 2);
			} else {
				if (b == "class") {
					return a.className.replace(/\sie7_\w+/g, "");
				} else {
					if (b == "style") {
						return a.style.cssText;
					} else {
						return c.nodeValue;
					}
				}
			}
		}
		return null;
	};
	var cc = "colSpan,rowSpan,vAlign,dateTime,accessKey,tabIndex,encType,maxLength,readOnly,longDesc";
	bf(bG, cN(cc.toLowerCase().split(","), cc.split(",")));
	IE7._3 = function (a) {
		while (a && (a = a.nextSibling) && (a.nodeType != 1 || a.nodeName == "!")) {
			continue;
		}
		return a;
	};
	IE7._4 = function (a) {
		while (a && (a = a.previousSibling) && (a.nodeType != 1 || a.nodeName == "!")) {
			continue;
		}
		return a;
	};
	var cW = /([\s>+~,]|[^(]\+|^)([#.:\[])/g, cX = /(^|,)([^\s>+~])/g, cY = /\s*([\s>+~(),]|^|$)\s*/g, cd = /\s\*\s/g;
	var ce = H.extend({constructor:function (a) {
		this.base(a);
		this.sorter = new H;
		this.sorter.add(/:not\([^)]*\)/, H.IGNORE);
		this.sorter.add(/([ >](\*|[\w-]+))([^: >+~]*)(:\w+-child(\([^)]+\))?)([^: >+~]*)/, "$1$3$6$4");
	}, ignoreCase:true, escape:function (a) {
		return this.optimise(this.format(a));
	}, format:function (a) {
		return a.replace(cY, "$1").replace(cX, "$1 $2").replace(cW, "$1*$2");
	}, optimise:function (a) {
		return this.sorter.exec(a.replace(cd, ">* "));
	}, unescape:function (a) {
		return bg(a);
	}});
	var cf = {"":"%1!=null", "=":"%1=='%2'", "~=":/(^| )%1( |$)/, "|=":/^%1(-|$)/, "^=":/^%1/, "$=":/%1$/, "*=":/%1/};
	var bH = {"first-child":"!IE7._4(e%1)", "link":"e%1.currentStyle['ie7-link']=='link'", "visited":"e%1.currentStyle['ie7-link']=='visited'"};
	var bI = "var p%2=0,i%2,e%2,n%2=e%1.";
	var cg = "e%1.sourceIndex";
	var ch = "var g=" + cg + ";if(!p[g]){p[g]=1;";
	var ci = "r[r.length]=e%1;if(s)return e%1;";
	var cj = "var _k=function(e0,s){IE7._1++;var r=[],p={},reg=[%1],d=document;";
	var I;
	var l;
	var p;
	var y;
	var Y;
	var bj = {};
	var E = new ce({" (\\*|[\\w-]+)#([\\w-]+)":function (a, b, c) {
		p = false;
		var d = "var e%2=IE7._e(d,'%4');if(e%2&&";
		if (b != "*") {
			d += "e%2.nodeName=='%3'&&";
		}
		d += "(e%1==d||e%1.contains(e%2))){";
		if (y) {
			d += i("i%1=n%1.length;", y);
		}
		return i(d, l++, l, b.toUpperCase(), c);
	}, " (\\*|[\\w-]+)":function (a, b) {
		Y++;
		p = b == "*";
		var c = bI;
		c += (p && bi) ? "all" : "getElementsByTagName('%3')";
		c += ";for(i%2=0;(e%2=n%2[i%2]);i%2++){";
		return i(c, l++, y = l, b.toUpperCase());
	}, ">(\\*|[\\w-]+)":function (a, b) {
		var c = y;
		p = b == "*";
		var d = bI;
		d += c ? "children" : "childNodes";
		if (!p && c) {
			d += ".tags('%3')";
		}
		d += ";for(i%2=0;(e%2=n%2[i%2]);i%2++){";
		if (p) {
			d += "if(e%2.nodeType==1){";
			p = bi;
		} else {
			if (!c) {
				d += "if(e%2.nodeName=='%3'){";
			}
		}
		return i(d, l++, y = l, b.toUpperCase());
	}, "\\+(\\*|[\\w-]+)":function (a, b) {
		var c = "";
		if (p) {
			c += "if(e%1.nodeName!='!'){";
		}
		p = false;
		c += "e%1=IE7._3(e%1);if(e%1";
		if (b != "*") {
			c += "&&e%1.nodeName=='%2'";
		}
		c += "){";
		return i(c, l, b.toUpperCase());
	}, "~(\\*|[\\w-]+)":function (a, b) {
		var c = "";
		if (p) {
			c += "if(e%1.nodeName!='!'){";
		}
		p = false;
		Y = 2;
		c += "while(e%1=e%1.nextSibling){if(e%1.ie7_adjacent==IE7._1)break;if(";
		if (b == "*") {
			c += "e%1.nodeType==1";
			if (bi) {
				c += "&&e%1.nodeName!='!'";
			}
		} else {
			c += "e%1.nodeName=='%2'";
		}
		c += "){e%1.ie7_adjacent=IE7._1;";
		return i(c, l, b.toUpperCase());
	}, "#([\\w-]+)":function (a, b) {
		p = false;
		var c = "if(e%1.id=='%2'){";
		if (y) {
			c += i("i%1=n%1.length;", y);
		}
		return i(c, l, b);
	}, "\\.([\\w-]+)":function (a, b) {
		p = false;
		I.push(new RegExp("(^|\\s)" + W(b) + "(\\s|$)"));
		return i("if(e%1.className&&reg[%2].test(e%1.className)){", l, I.length - 1);
	}, "\\[([\\w-]+)\\s*([^=]?=)?\\s*([^\\]]*)\\]":function (a, b, c, d) {
		var f = bG[b] || b;
		if (c) {
			var g = "e%1.getAttribute('%2',2)";
			if (!cb.test(b)) {
				g = "e%1.%3||" + g;
			}
			b = i("(" + g + ")", l, b, f);
		} else {
			b = i("IE7._f(e%1,'%2')", l, b);
		}
		var h = cf[c || ""] || "0";
		if (h && h.source) {
			I.push(new RegExp(i(h.source, W(E.unescape(d)))));
			h = "reg[%2].test(%1)";
			d = I.length - 1;
		}
		return "if(" + i(h, b, d) + "){";
	}, ":+([\\w-]+)(\\(([^)]+)\\))?":function (a, b, c, d) {
		b = bH[b];
		return "if(" + (b ? i(b, l, d || "") : "0") + "){";
	}});
	var ck = /a(#[\w-]+)?(\.[\w-]+)?:(hover|active)/i;
	var cl = /\s*\{\s*/, cm = /\s*\}\s*/, cn = /\s*\,\s*/;
	var co = /(.*)(:first-(line|letter))/;
	var z = document.styleSheets;
	IE7.CSS = new (bZ.extend({parser:new bU, screen:"", print:"", styles:[], rules:[], pseudoClasses:m < 7 ? "first\\-child" : "", dynamicPseudoClasses:{toString:function () {
		var a = [];
		for (var b in this) {
			a.push(b);
		}
		return a.join("|");
	}}, init:function () {
		var a = "^\x01$";
		var b = "\\[class=?[^\\]]*\\]";
		var c = [];
		if (this.pseudoClasses) {
			c.push(this.pseudoClasses);
		}
		var d = this.dynamicPseudoClasses.toString();
		if (d) {
			c.push(d);
		}
		c = c.join("|");
		var f = m < 7 ? ["[>+~[(]|([:.])\\w+\\1"] : [b];
		if (c) {
			f.push(":(" + c + ")");
		}
		this.UNKNOWN = new RegExp(f.join("|") || a, "i");
		var g = m < 7 ? ["\\[[^\\]]+\\]|[^\\s(\\[]+\\s*[+~]"] : [b];
		var h = g.concat();
		if (c) {
			h.push(":(" + c + ")");
		}
		o.COMPLEX = new RegExp(h.join("|") || a, "ig");
		if (this.pseudoClasses) {
			g.push(":(" + this.pseudoClasses + ")");
		}
		O.COMPLEX = new RegExp(g.join("|") || a, "i");
		O.MATCH = new RegExp(d ? "(.*):(" + d + ")(.*)" : a, "i");
		this.createStyleSheet();
		this.refresh();
	}, addEventHandler:function () {
		v.apply(null, arguments);
	}, addFix:function (a, b) {
		this.parser.add(a, b);
	}, addRecalc:function (c, d, f, g) {
		d = new RegExp("([{;\\s])" + c + "\\s*:\\s*" + d + "[^;}]*");
		var h = this.recalcs.length;
		if (g) {
			g = c + ":" + g;
		}
		this.addFix(d, function (a, b) {
			return (g ? b + g : a) + ";ie7-" + a.slice(1) + ";ie7_recalc" + h + ":1";
		});
		this.recalcs.push(arguments);
		return h;
	}, apply:function () {
		this.getInlineStyles();
		new bJ("screen");
		this.trash();
	}, createStyleSheet:function () {
		this.styleSheet = document.createStyleSheet();
		this.styleSheet.ie7 = true;
		this.styleSheet.owningElement.ie7 = true;
		this.styleSheet.cssText = J;
	}, getInlineStyles:function () {
		var a = document.getElementsByTagName("style"), b;
		for (var c = a.length - 1; (b = a[c]); c--) {
			if (!b.disabled && !b.ie7) {
				this.styles.push(b.innerHTML);
			}
		}
	}, getText:function (a, b) {
		try {
			var c = a.cssText;
		}
		catch (e) {
			c = "";
		}
		if (K) {
			c = cD(a.href, b) || c;
		}
		return c;
	}, recalc:function () {
		this.screen.recalc();
		var a = /ie7_recalc\d+/g;
		var b = J.match(/[{,]/g).length;
		var c = b + (this.screen.cssText.match(/\{/g) || "").length;
		var d = this.styleSheet.rules, f;
		var g, h, j, q, r, k, u, n;
		for (r = b; r < c; r++) {
			f = d[r];
			var s = f.style.cssText;
			if (f && (g = s.match(a))) {
				j = x(f.selectorText);
				if (j.length) {
					for (k = 0; k < g.length; k++) {
						n = g[k];
						h = IE7.CSS.recalcs[n.slice(10)][2];
						for (u = 0; (q = j[u]); u++) {
							if (q.currentStyle[n]) {
								h(q, s);
							}
						}
					}
				}
			}
		}
	}, refresh:function () {
		this.styleSheet.cssText = J + this.screen + this.print;
	}, trash:function () {
		for (var a = 0; a < z.length; a++) {
			if (!z[a].ie7) {
				try {
					var b = z[a].cssText;
				}
				catch (e) {
					b = "";
				}
				if (b) {
					z[a].cssText = "";
				}
			}
		}
	}}));
	var bJ = B.extend({constructor:function (a) {
		this.media = a;
		this.load();
		IE7.CSS[a] = this;
		IE7.CSS.refresh();
	}, createRule:function (a, b) {
		if (IE7.CSS.UNKNOWN.test(a)) {
			var c;
			if (F && (c = a.match(F.MATCH))) {
				return new F(c[1], c[2], b);
			} else {
				if (c = a.match(O.MATCH)) {
					if (!ck.test(c[0]) || O.COMPLEX.test(c[0])) {
						return new O(a, c[1], c[2], c[3], b);
					}
				} else {
					return new o(a, b);
				}
			}
		}
		return a + " {" + b + "}";
	}, getText:function () {
		var h = [].concat(IE7.CSS.styles);
		var j = /@media\s+([^{]*)\{([^@]+\})\s*\}/gi;
		var q = /\ball\b|^$/i, r = /\bscreen\b/i, k = /\bprint\b/i;
		function u(a, b) {
			n.value = b;
			return a.replace(j, n);
		}
		function n(a, b, c) {
			b = s(b);
			switch (b) {
			  case "screen":
			  case "print":
				if (b != n.value) {
					return "";
				}
			  case "all":
				return c;
			}
			return "";
		}
		function s(a) {
			if (q.test(a)) {
				return "all";
			} else {
				if (r.test(a)) {
					return (k.test(a)) ? "all" : "screen";
				} else {
					if (k.test(a)) {
						return "print";
					}
				}
			}
		}
		var R = this;
		function S(a, b, c, d) {
			var f = "";
			if (!d) {
				c = s(a.media);
				d = 0;
			}
			if (c == "all" || c == R.media) {
				if (d < 3) {
					for (var g = 0; g < a.imports.length; g++) {
						f += S(a.imports[g], by(a.href, b), c, d + 1);
					}
				}
				f += cS(a.href ? cy(a, b) : h.pop() || "");
				f = u(f, R.media);
			}
			return f;
		}
		var bw = {};
		function cy(a, b) {
			var c = bc(a.href, b);
			if (bw[c]) {
				return "";
			}
			bw[c] = (a.disabled) ? "" : cA(IE7.CSS.getText(a, b), by(a.href, b));
			return bw[c];
		}
		var cz = /(url\s*\(\s*['"]?)([\w\.]+[^:\)]*['"]?\))/gi;
		function cA(a, b) {
			return a.replace(cz, "$1" + b.slice(0, b.lastIndexOf("/") + 1) + "$2");
		}
		for (var T = 0; T < z.length; T++) {
			if (!z[T].disabled && !z[T].ie7) {
				this.cssText += S(z[T]);
			}
		}
	}, load:function () {
		this.cssText = "";
		this.getText();
		this.parse();
		this.cssText = bg(this.cssText);
		bd = {};
	}, parse:function () {
		this.cssText = IE7.CSS.parser.exec(this.cssText);
		var a = IE7.CSS.rules.length;
		var b = this.cssText.split(cm), c;
		var d, f, g, h;
		for (g = 0; g < b.length; g++) {
			c = b[g].split(cl);
			d = c[0].split(cn);
			f = c[1];
			for (h = 0; h < d.length; h++) {
				d[h] = f ? this.createRule(d[h], f) : "";
			}
			b[g] = d.join("\n");
		}
		this.cssText = b.join("\n");
		this.rules = IE7.CSS.rules.slice(a);
	}, recalc:function () {
		var a, b;
		for (b = 0; (a = this.rules[b]); b++) {
			a.recalc();
		}
	}, toString:function () {
		return "@media " + this.media + "{" + this.cssText + "}";
	}});
	var F;
	var o = IE7.Rule = B.extend({constructor:function (a, b) {
		this.id = IE7.CSS.rules.length;
		this.className = o.PREFIX + this.id;
		a = a.match(co) || a || "*";
		this.selector = a[1] || a;
		this.selectorText = this.parse(this.selector) + (a[2] || "");
		this.cssText = b;
		this.MATCH = new RegExp("\\s" + this.className + "(\\s|$)", "g");
		IE7.CSS.rules.push(this);
		this.init();
	}, init:U, add:function (a) {
		a.className += " " + this.className;
	}, recalc:function () {
		var a = x(this.selector);
		for (var b = 0; b < a.length; b++) {
			this.add(a[b]);
		}
	}, parse:function (a) {
		var b = a.replace(o.CHILD, " ").replace(o.COMPLEX, "");
		if (m < 7) {
			b = b.replace(o.MULTI, "");
		}
		var c = L(b, o.TAGS).length - L(a, o.TAGS).length;
		var d = L(b, o.CLASSES).length - L(a, o.CLASSES).length + 1;
		while (d > 0 && o.CLASS.test(b)) {
			b = b.replace(o.CLASS, "");
			d--;
		}
		while (c > 0 && o.TAG.test(b)) {
			b = b.replace(o.TAG, "$1*");
			c--;
		}
		b += "." + this.className;
		d = Math.min(d, 2);
		c = Math.min(c, 2);
		var f = -10 * d - c;
		if (f > 0) {
			b = b + "," + o.MAP[f] + " " + b;
		}
		return b;
	}, remove:function (a) {
		a.className = a.className.replace(this.MATCH, "$1");
	}, toString:function () {
		return i("%1 {%2}", this.selectorText, this.cssText);
	}}, {CHILD:/>/g, CLASS:/\.[\w-]+/, CLASSES:/[.:\[]/g, MULTI:/(\.[\w-]+)+/g, PREFIX:"ie7_class", TAG:/^\w+|([\s>+~])\w+/, TAGS:/^\w|[\s>+~]\w/g, MAP:{1:"html", 2:"html body", 10:".ie7_html", 11:"html.ie7_html", 12:"html.ie7_html body", 20:".ie7_html .ie7_body", 21:"html.ie7_html .ie7_body", 22:"html.ie7_html body.ie7_body"}});
	var O = o.extend({constructor:function (a, b, c, d, f) {
		this.attach = b || "*";
		this.dynamicPseudoClass = IE7.CSS.dynamicPseudoClasses[c];
		this.target = d;
		this.base(a, f);
	}, recalc:function () {
		var a = x(this.attach), b;
		for (var c = 0; b = a[c]; c++) {
			var d = this.target ? x(this.target, b) : [b];
			if (d.length) {
				this.dynamicPseudoClass.apply(b, d, this);
			}
		}
	}});
	var A = B.extend({constructor:function (a, b) {
		this.name = a;
		this.apply = b;
		this.instances = {};
		IE7.CSS.dynamicPseudoClasses[a] = this;
	}, register:function (a) {
		var b = a[2];
		a.id = b.id + a[0].uniqueID;
		if (!this.instances[a.id]) {
			var c = a[1], d;
			for (d = 0; d < c.length; d++) {
				b.add(c[d]);
			}
			this.instances[a.id] = a;
		}
	}, unregister:function (a) {
		if (this.instances[a.id]) {
			var b = a[2];
			var c = a[1], d;
			for (d = 0; d < c.length; d++) {
				b.remove(c[d]);
			}
			delete this.instances[a.id];
		}
	}});
	if (m < 7) {
		var Z = new A("hover", function (a) {
			var b = arguments;
			IE7.CSS.addEventHandler(a, m < 5.5 ? "onmouseover" : "onmouseenter", function () {
				Z.register(b);
			});
			IE7.CSS.addEventHandler(a, m < 5.5 ? "onmouseout" : "onmouseleave", function () {
				Z.unregister(b);
			});
		});
		v(document, "onmouseup", function () {
			var a = Z.instances;
			for (var b in a) {
				if (!a[b][0].contains(event.srcElement)) {
					Z.unregister(a[b]);
				}
			}
		});
	}
	IE7.CSS.addRecalc("[\\w-]+", "inherit", function (c, d) {
		var f = d.match(/[\w-]+\s*:\s*inherit/g);
		for (var g = 0; g < f.length; g++) {
			var h = f[g].replace(/ie7\-|\s*:\s*inherit/g, "").replace(/\-([a-z])/g, function (a, b) {
				return b.toUpperCase();
			});
			c.runtimeStyle[h] = c.parentElement.currentStyle[h];
		}
	});
	IE7.HTML = new (bZ.extend({fixed:{}, init:U, addFix:function () {
		this.fixes.push(arguments);
	}, apply:function () {
		for (var a = 0; a < this.fixes.length; a++) {
			var b = x(this.fixes[a][0]);
			var c = this.fixes[a][1];
			for (var d = 0; d < b.length; d++) {
				c(b[d]);
			}
		}
	}, addRecalc:function () {
		this.recalcs.push(arguments);
	}, recalc:function () {
		for (var a = 0; a < this.recalcs.length; a++) {
			var b = x(this.recalcs[a][0]);
			var c = this.recalcs[a][1], d;
			var f = Math.pow(2, a);
			for (var g = 0; (d = b[g]); g++) {
				var h = d.uniqueID;
				if ((this.fixed[h] & f) == 0) {
					d = c(d) || d;
					this.fixed[h] |= f;
				}
			}
		}
	}}));
	if (m < 7) {
		document.createElement("abbr");
		IE7.HTML.addRecalc("label", function (a) {
			if (!a.htmlFor) {
				var b = x("input,textarea", a, true);
				if (b) {
					v(a, "onclick", function () {
						b.click();
					});
				}
			}
		});
	}
	var P = "[.\\d]";
	new function (_) {
		var layout = IE7.Layout = this;
		J += "*{boxSizing:content-box}";
		IE7.hasLayout = m < 5.5 ? function (a) {
			return a.clientWidth;
		} : function (a) {
			return a.currentStyle.hasLayout;
		};
		layout.boxSizing = function (a) {
			if (!IE7.hasLayout(a)) {
				a.style.height = "0cm";
				if (a.currentStyle.verticalAlign == "auto") {
					a.runtimeStyle.verticalAlign = "top";
				}
				collapseMargins(a);
			}
		};
		function collapseMargins(a) {
			if (a != t && a.currentStyle.position != "absolute") {
				collapseMargin(a, "marginTop");
				collapseMargin(a, "marginBottom");
			}
		}
		function collapseMargin(a, b) {
			if (!a.runtimeStyle[b]) {
				var c = a.parentElement;
				if (c && IE7.hasLayout(c) && !IE7[b == "marginTop" ? "_4" : "_3"](a)) {
					return;
				}
				var d = x(">*:" + (b == "marginTop" ? "first" : "last") + "-child", a, true);
				if (d && d.currentStyle.styleFloat == "none" && IE7.hasLayout(d)) {
					collapseMargin(d, b);
					margin = _b(a, a.currentStyle[b]);
					childMargin = _b(d, d.currentStyle[b]);
					if (margin < 0 || childMargin < 0) {
						a.runtimeStyle[b] = margin + childMargin;
					} else {
						a.runtimeStyle[b] = Math.max(childMargin, margin);
					}
					d.runtimeStyle[b] = "0px";
				}
			}
		}
		function _b(a, b) {
			return b == "auto" ? 0 : D(a, b);
		}
		var UNIT = /^[.\d][\w%]*$/, AUTO = /^(auto|0cm)$/;
		var applyWidth, applyHeight;
		IE7.Layout.borderBox = function (a) {
			applyWidth(a);
			applyHeight(a);
		};
		var fixWidth = function (g) {
			applyWidth = function (a) {
				if (!M.test(a.currentStyle.width)) {
					h(a);
				}
				collapseMargins(a);
			};
			function h(a, b) {
				if (!a.runtimeStyle.fixedWidth) {
					if (!b) {
						b = a.currentStyle.width;
					}
					a.runtimeStyle.fixedWidth = (UNIT.test(b)) ? Math.max(0, r(a, b)) : b;
					N(a, "width", a.runtimeStyle.fixedWidth);
				}
			}
			function j(a) {
				if (!bh(a)) {
					var b = a.offsetParent;
					while (b && !IE7.hasLayout(b)) {
						b = b.offsetParent;
					}
				}
				return (b || t).clientWidth;
			}
			function q(a, b) {
				if (M.test(b)) {
					return parseInt(parseFloat(b) / 100 * j(a));
				}
				return D(a, b);
			}
			var r = function (a, b) {
				var c = a.currentStyle["box-sizing"] == "border-box";
				var d = 0;
				if (G && !c) {
					d += k(a) + u(a, "padding");
				} else {
					if (!G && c) {
						d -= k(a) + u(a, "padding");
					}
				}
				return q(a, b) + d;
			};
			function k(a) {
				return a.offsetWidth - a.clientWidth;
			}
			function u(a, b) {
				return q(a, a.currentStyle[b + "Left"]) + q(a, a.currentStyle[b + "Right"]);
			}
			J += "*{minWidth:none;maxWidth:none;min-width:none;max-width:none}";
			layout.minWidth = function (a) {
				if (a.currentStyle["min-width"] != null) {
					a.style.minWidth = a.currentStyle["min-width"];
				}
				if (X(arguments.callee, a, a.currentStyle.minWidth != "none")) {
					layout.boxSizing(a);
					h(a);
					n(a);
				}
			};
			eval("IE7.Layout.maxWidth=" + String(layout.minWidth).replace(/min/g, "max"));
			function n(a) {
				var b = a.getBoundingClientRect();
				var c = b.right - b.left;
				if (a.currentStyle.minWidth != "none" && c <= r(a, a.currentStyle.minWidth)) {
					a.runtimeStyle.width = a.currentStyle.minWidth;
				} else {
					if (a.currentStyle.maxWidth != "none" && c >= r(a, a.currentStyle.maxWidth)) {
						a.runtimeStyle.width = a.currentStyle.maxWidth;
					} else {
						a.runtimeStyle.width = a.runtimeStyle.fixedWidth;
					}
				}
			}
			function s(a) {
				if (X(s, a, /^(fixed|absolute)$/.test(a.currentStyle.position) && bF(a, "left") != "auto" && bF(a, "right") != "auto" && AUTO.test(bF(a, "width")))) {
					R(a);
					IE7.Layout.boxSizing(a);
				}
			}
			IE7.Layout.fixRight = s;
			function R(a) {
				var b = q(a, a.runtimeStyle._c || a.currentStyle.left);
				var c = j(a) - q(a, a.currentStyle.right) - b - u(a, "margin");
				if (parseInt(a.runtimeStyle.width) == c) {
					return;
				}
				a.runtimeStyle.width = "";
				if (bh(a) || g || a.offsetWidth < c) {
					if (!G) {
						c -= k(a) + u(a, "padding");
					}
					if (c < 0) {
						c = 0;
					}
					a.runtimeStyle.fixedWidth = c;
					N(a, "width", c);
				}
			}
			var S = 0;
			bC(function () {
				if (!t) {
					return;
				}
				var a, b = (S < t.clientWidth);
				S = t.clientWidth;
				var c = layout.minWidth.elements;
				for (a in c) {
					var d = c[a];
					var f = (parseInt(d.runtimeStyle.width) == r(d, d.currentStyle.minWidth));
					if (b && f) {
						d.runtimeStyle.width = "";
					}
					if (b == f) {
						n(d);
					}
				}
				var c = layout.maxWidth.elements;
				for (a in c) {
					var d = c[a];
					var f = (parseInt(d.runtimeStyle.width) == r(d, d.currentStyle.maxWidth));
					if (!b && f) {
						d.runtimeStyle.width = "";
					}
					if (b != f) {
						n(d);
					}
				}
				for (a in s.elements) {
					R(s.elements[a]);
				}
			});
			if (G) {
				IE7.CSS.addRecalc("width", P, applyWidth);
			}
			if (m < 7) {
				IE7.CSS.addRecalc("min-width", P, layout.minWidth);
				IE7.CSS.addRecalc("max-width", P, layout.maxWidth);
				IE7.CSS.addRecalc("right", P, s);
			}
		};
		eval("var fixHeight=" + C(fixWidth));
		fixWidth();
		fixHeight(true);
	};
	var bk = bc("blank.gif", cC);
	var bl = "DXImageTransform.Microsoft.AlphaImageLoader";
	var bK = "progid:" + bl + "(src='%1',sizingMethod='%2')";
	var bm;
	var Q = [];
	function bL(a) {
		if (bm.test(a.src)) {
			var b = new Image(a.width, a.height);
			b.onload = function () {
				a.width = b.width;
				a.height = b.height;
				b = null;
			};
			b.src = a.src;
			a.pngSrc = a.src;
			bo(a);
		}
	}
	if (m >= 5.5 && m < 7) {
		IE7.CSS.addFix(/background(-image)?\s*:\s*([^};]*)?url\(([^\)]+)\)([^;}]*)?/, function (a, b, c, d, f) {
			d = bB(d);
			return bm.test(d) ? "filter:" + i(bK, d, "crop") + ";zoom:1;background" + (b || "") + ":" + (c || "") + "none" + (f || "") : a;
		});
		IE7.HTML.addRecalc("img,input", function (a) {
			if (a.tagName == "INPUT" && a.type != "image") {
				return;
			}
			bL(a);
			v(a, "onpropertychange", function () {
				if (!bn && event.propertyName == "src" && a.src.indexOf(bk) == -1) {
					bL(a);
				}
			});
		});
		var bn = false;
		v(window, "onbeforeprint", function () {
			bn = true;
			for (var a = 0; a < Q.length; a++) {
				cp(Q[a]);
			}
		});
		v(window, "onafterprint", function () {
			for (var a = 0; a < Q.length; a++) {
				bo(Q[a]);
			}
			bn = false;
		});
	}
	function bo(a, b) {
		var c = a.filters[bl];
		if (c) {
			c.src = a.src;
			c.enabled = true;
		} else {
			a.runtimeStyle.filter = i(bK, a.src, b || "scale");
			Q.push(a);
		}
		a.src = bk;
	}
	function cp(a) {
		a.src = a.pngSrc;
		a.filters[bl].enabled = false;
	}
	new function (_) {
		if (m >= 7) {
			return;
		}
		IE7.CSS.addRecalc("position", "fixed", _8, "absolute");
		IE7.CSS.addRecalc("background(-attachment)?", "[^};]*fixed", _5);
		var $viewport = G ? "body" : "documentElement";
		function _6() {
			if (w.currentStyle.backgroundAttachment != "fixed") {
				if (w.currentStyle.backgroundImage == "none") {
					w.runtimeStyle.backgroundRepeat = "no-repeat";
					w.runtimeStyle.backgroundImage = "url(" + bk + ")";
				}
				w.runtimeStyle.backgroundAttachment = "fixed";
			}
			_6 = U;
		}
		var _0 = ca("img");
		function _2(a) {
			return a ? bh(a) || _2(a.parentElement) : false;
		}
		function _d(a, b, c) {
			setTimeout("document.all." + a.uniqueID + ".runtimeStyle.setExpression('" + b + "','" + c + "')", 0);
		}
		function _5(a) {
			if (X(_5, a, a.currentStyle.backgroundAttachment == "fixed" && !a.contains(w))) {
				_6();
				bgLeft(a);
				bgTop(a);
				_a(a);
			}
		}
		function _a(a) {
			_0.src = a.currentStyle.backgroundImage.slice(5, -2);
			var b = a.canHaveChildren ? a : a.parentElement;
			b.appendChild(_0);
			setOffsetLeft(a);
			setOffsetTop(a);
			b.removeChild(_0);
		}
		function bgLeft(a) {
			a.style.backgroundPositionX = a.currentStyle.backgroundPositionX;
			if (!_2(a)) {
				_d(a, "backgroundPositionX", "(parseInt(runtimeStyle.offsetLeft)+document." + $viewport + ".scrollLeft)||0");
			}
		}
		eval(C(bgLeft));
		function setOffsetLeft(a) {
			var b = _2(a) ? "backgroundPositionX" : "offsetLeft";
			a.runtimeStyle[b] = getOffsetLeft(a, a.style.backgroundPositionX) - a.getBoundingClientRect().left - a.clientLeft + 2;
		}
		eval(C(setOffsetLeft));
		function getOffsetLeft(a, b) {
			switch (b) {
			  case "left":
			  case "top":
				return 0;
			  case "right":
			  case "bottom":
				return t.clientWidth - _0.offsetWidth;
			  case "center":
				return (t.clientWidth - _0.offsetWidth) / 2;
			  default:
				if (M.test(b)) {
					return parseInt((t.clientWidth - _0.offsetWidth) * parseFloat(b) / 100);
				}
				_0.style.left = b;
				return _0.offsetLeft;
			}
		}
		eval(C(getOffsetLeft));
		function _8(a) {
			if (X(_8, a, bh(a))) {
				N(a, "position", "absolute");
				N(a, "left", a.currentStyle.left);
				N(a, "top", a.currentStyle.top);
				_6();
				IE7.Layout.fixRight(a);
				_7(a);
			}
		}
		function _7(a, b) {
			positionTop(a, b);
			positionLeft(a, b, true);
			if (!a.runtimeStyle.autoLeft && a.currentStyle.marginLeft == "auto" && a.currentStyle.right != "auto") {
				var c = t.clientWidth - getPixelWidth(a, a.currentStyle.right) - getPixelWidth(a, a.runtimeStyle._c) - a.clientWidth;
				if (a.currentStyle.marginRight == "auto") {
					c = parseInt(c / 2);
				}
				if (_2(a.offsetParent)) {
					a.runtimeStyle.pixelLeft += c;
				} else {
					a.runtimeStyle.shiftLeft = c;
				}
			}
			clipWidth(a);
			clipHeight(a);
		}
		function clipWidth(a) {
			var b = a.runtimeStyle.fixWidth;
			a.runtimeStyle.borderRightWidth = "";
			a.runtimeStyle.width = b ? getPixelWidth(a, b) : "";
			if (a.currentStyle.width != "auto") {
				var c = a.getBoundingClientRect();
				var d = a.offsetWidth - t.clientWidth + c.left - 2;
				if (d >= 0) {
					a.runtimeStyle.borderRightWidth = "0px";
					d = Math.max(D(a, a.currentStyle.width) - d, 0);
					N(a, "width", d);
					return d;
				}
			}
		}
		eval(C(clipWidth));
		function positionLeft(a, b) {
			if (!b && M.test(a.currentStyle.width)) {
				a.runtimeStyle.fixWidth = a.currentStyle.width;
			}
			if (a.runtimeStyle.fixWidth) {
				a.runtimeStyle.width = getPixelWidth(a, a.runtimeStyle.fixWidth);
			}
			a.runtimeStyle.shiftLeft = 0;
			a.runtimeStyle._c = a.currentStyle.left;
			a.runtimeStyle.autoLeft = a.currentStyle.right != "auto" && a.currentStyle.left == "auto";
			a.runtimeStyle.left = "";
			a.runtimeStyle.screenLeft = getScreenLeft(a);
			a.runtimeStyle.pixelLeft = a.runtimeStyle.screenLeft;
			if (!b && !_2(a.offsetParent)) {
				_d(a, "pixelLeft", "runtimeStyle.screenLeft+runtimeStyle.shiftLeft+document." + $viewport + ".scrollLeft");
			}
		}
		eval(C(positionLeft));
		function getScreenLeft(a) {
			var b = a.offsetLeft, c = 1;
			if (a.runtimeStyle.autoLeft) {
				b = t.clientWidth - a.offsetWidth - getPixelWidth(a, a.currentStyle.right);
			}
			if (a.currentStyle.marginLeft != "auto") {
				b -= getPixelWidth(a, a.currentStyle.marginLeft);
			}
			while (a = a.offsetParent) {
				if (a.currentStyle.position != "static") {
					c = -1;
				}
				b += a.offsetLeft * c;
			}
			return b;
		}
		eval(C(getScreenLeft));
		function getPixelWidth(a, b) {
			return M.test(b) ? parseInt(parseFloat(b) / 100 * t.clientWidth) : D(a, b);
		}
		eval(C(getPixelWidth));
		function _j() {
			var a = _5.elements;
			for (var b in a) {
				_a(a[b]);
			}
			a = _8.elements;
			for (b in a) {
				_7(a[b], true);
				_7(a[b], true);
			}
			_9 = 0;
		}
		var _9;
		bC(function () {
			if (!_9) {
				_9 = setTimeout(_j, 0);
			}
		});
	};
	var bp = {backgroundColor:"transparent", backgroundImage:"none", backgroundPositionX:null, backgroundPositionY:null, backgroundRepeat:null, borderTopWidth:0, borderRightWidth:0, borderBottomWidth:0, borderLeftStyle:"none", borderTopStyle:"none", borderRightStyle:"none", borderBottomStyle:"none", borderLeftWidth:0, height:null, marginTop:0, marginBottom:0, marginRight:0, marginLeft:0, width:"100%"};
	IE7.CSS.addRecalc("overflow", "visible", function (a) {
		if (a.parentNode.ie7_wrapped) {
			return;
		}
		if (IE7.Layout && a.currentStyle["max-height"] != "auto") {
			IE7.Layout.maxHeight(a);
		}
		if (a.currentStyle.marginLeft == "auto") {
			a.style.marginLeft = 0;
		}
		if (a.currentStyle.marginRight == "auto") {
			a.style.marginRight = 0;
		}
		var b = document.createElement(bN);
		b.ie7_wrapped = a;
		for (var c in bp) {
			b.style[c] = a.currentStyle[c];
			if (bp[c] != null) {
				a.runtimeStyle[c] = bp[c];
			}
		}
		b.style.display = "block";
		b.style.position = "relative";
		a.runtimeStyle.position = "absolute";
		a.parentNode.insertBefore(b, a);
		b.appendChild(a);
	});
	function cq() {
		var f = "xx-small,x-small,small,medium,large,x-large,xx-large".split(",");
		for (var g = 0; g < f.length; g++) {
			f[f[g]] = f[g - 1] || "0.67em";
		}
		IE7.CSS.addFix(/(font(-size)?\s*:\s*)([\w.-]+)/, function (a, b, c, d) {
			return b + (f[d] || d);
		});
		if (m < 6) {
			var h = /^\-/, j = /(em|ex)$/i;
			var q = /em$/i, r = /ex$/i;
			D = function (a, b) {
				if (bY.test(b)) {
					return parseInt(b) || 0;
				}
				var c = h.test(b) ? -1 : 1;
				if (j.test(b)) {
					c *= u(a);
				}
				k.style.width = (c < 0) ? b.slice(1) : b;
				w.appendChild(k);
				b = c * k.offsetWidth;
				k.removeNode();
				return parseInt(b);
			};
			var k = ca();
			function u(a) {
				var b = 1;
				k.style.fontFamily = a.currentStyle.fontFamily;
				k.style.lineHeight = a.currentStyle.lineHeight;
				while (a != w) {
					var c = a.currentStyle["ie7-font-size"];
					if (c) {
						if (q.test(c)) {
							b *= parseFloat(c);
						} else {
							if (M.test(c)) {
								b *= (parseFloat(c) / 100);
							} else {
								if (r.test(c)) {
									b *= (parseFloat(c) / 2);
								} else {
									k.style.fontSize = c;
									return 1;
								}
							}
						}
					}
					a = a.parentElement;
				}
				return b;
			}
			IE7.CSS.addFix(/cursor\s*:\s*pointer/, "cursor:hand");
			IE7.CSS.addFix(/display\s*:\s*list-item/, "display:block");
		}
		function n(a) {
			if (m < 5.5) {
				IE7.Layout.boxSizing(a.parentElement);
			}
			var b = a.parentElement;
			var c = b.offsetWidth - a.offsetWidth - s(b);
			var d = (a.currentStyle["ie7-margin"] && a.currentStyle.marginRight == "auto") || a.currentStyle["ie7-margin-right"] == "auto";
			switch (b.currentStyle.textAlign) {
			  case "right":
				c = d ? parseInt(c / 2) : 0;
				a.runtimeStyle.marginRight = c + "px";
				break;
			  case "center":
				if (d) {
					c = 0;
				}
			  default:
				if (d) {
					c /= 2;
				}
				a.runtimeStyle.marginLeft = parseInt(c) + "px";
			}
		}
		function s(a) {
			return D(a, a.currentStyle.paddingLeft) + D(a, a.currentStyle.paddingRight);
		}
		IE7.CSS.addRecalc("margin(-left|-right)?", "[^};]*auto", function (a) {
			if (X(n, a, a.parentElement && a.currentStyle.display == "block" && a.currentStyle.marginLeft == "auto" && a.currentStyle.position != "absolute")) {
				n(a);
			}
		});
		bC(function () {
			for (var a in n.elements) {
				var b = n.elements[a];
				b.runtimeStyle.marginLeft = b.runtimeStyle.marginRight = "";
				n(b);
			}
		});
	}
	IE7._g = function (a) {
		a = a.firstChild;
		while (a) {
			if (a.nodeType == 3 || (a.nodeType == 1 && a.nodeName != "!")) {
				return false;
			}
			a = a.nextSibling;
		}
		return true;
	};
	IE7._h = function (a, b) {
		while (a && !a.getAttribute("lang")) {
			a = a.parentNode;
		}
		return a && new RegExp("^" + W(b), "i").test(a.getAttribute("lang"));
	};
	function cr(a, b, c, d) {
		d = /last/i.test(a) ? d + "+1-" : "";
		if (!isNaN(b)) {
			b = "0n+" + b;
		} else {
			if (b == "even") {
				b = "2n";
			} else {
				if (b == "odd") {
					b = "2n+1";
				}
			}
		}
		b = b.split("n");
		var f = b[0] ? (b[0] == "-") ? -1 : parseInt(b[0]) : 1;
		var g = parseInt(b[1]) || 0;
		var h = f < 0;
		if (h) {
			f = -f;
			if (f == 1) {
				g++;
			}
		}
		var j = i(f == 0 ? "%3%7" + (d + g) : "(%4%3-%2)%6%1%70%5%4%3>=%2", f, g, c, d, "&&", "%", "==");
		if (h) {
			j = "!(" + j + ")";
		}
		return j;
	}
	bH = {"link":"e%1.currentStyle['ie7-link']=='link'", "visited":"e%1.currentStyle['ie7-link']=='visited'", "checked":"e%1.checked", "contains":"e%1.innerText.indexOf('%2')!=-1", "disabled":"e%1.isDisabled", "empty":"IE7._g(e%1)", "enabled":"e%1.disabled===false", "first-child":"!IE7._4(e%1)", "lang":"IE7._h(e%1,'%2')", "last-child":"!IE7._3(e%1)", "only-child":"!IE7._4(e%1)&&!IE7._3(e%1)", "target":"e%1.id==location.hash.slice(1)", "indeterminate":"e%1.indeterminate"};
	IE7._i = function (a) {
		if (a.rows) {
			a.ie7_length = a.rows.length;
			a.ie7_lookup = "rowIndex";
		} else {
			if (a.cells) {
				a.ie7_length = a.cells.length;
				a.ie7_lookup = "cellIndex";
			} else {
				if (a.ie7_indexed != IE7._1) {
					var b = 0;
					var c = a.firstChild;
					while (c) {
						if (c.nodeType == 1 && c.nodeName != "!") {
							c.ie7_index = ++b;
						}
						c = c.nextSibling;
					}
					a.ie7_length = b;
					a.ie7_lookup = "ie7_index";
				}
			}
		}
		a.ie7_indexed = IE7._1;
		return a;
	};
	var ba = E[V];
	var cs = ba[ba.length - 1];
	ba.length--;
	E.merge({":not\\((\\*|[\\w-]+)?([^)]*)\\)":function (a, b, c) {
		var d = (b && b != "*") ? i("if(e%1.nodeName=='%2'){", l, b.toUpperCase()) : "";
		d += E.exec(c);
		return "if(!" + d.slice(2, -1).replace(/\)\{if\(/g, "&&") + "){";
	}, ":nth(-last)?-child\\(([^)]+)\\)":function (a, b, c) {
		p = false;
		b = i("e%1.parentNode.ie7_length", l);
		var d = "if(p%1!==e%1.parentNode)p%1=IE7._i(e%1.parentNode);";
		d += "var i=e%1[p%1.ie7_lookup];if(p%1.ie7_lookup!='ie7_index')i++;if(";
		return i(d, l) + cr(a, c, "i", b) + "){";
	}});
	ba.push(cs);
	var bM = "\\([^)]*\\)";
	if (IE7.CSS.pseudoClasses) {
		IE7.CSS.pseudoClasses += "|";
	}
	IE7.CSS.pseudoClasses += "before|after|last\\-child|only\\-child|empty|root|" + "not|nth\\-child|nth\\-last\\-child|contains|lang".split("|").join(bM + "|") + bM;
	bV.add(/::/, ":");
	var bb = new A("focus", function (a) {
		var b = arguments;
		IE7.CSS.addEventHandler(a, "onfocus", function () {
			bb.unregister(b);
			bb.register(b);
		});
		IE7.CSS.addEventHandler(a, "onblur", function () {
			bb.unregister(b);
		});
		if (a == document.activeElement) {
			bb.register(b);
		}
	});
	var bq = new A("active", function (a) {
		var b = arguments;
		IE7.CSS.addEventHandler(a, "onmousedown", function () {
			bq.register(b);
		});
	});
	v(document, "onmouseup", function () {
		var a = bq.instances;
		for (var b in a) {
			bq.unregister(a[b]);
		}
	});
	var br = new A("checked", function (a) {
		if (typeof a.checked != "boolean") {
			return;
		}
		var b = arguments;
		IE7.CSS.addEventHandler(a, "onpropertychange", function () {
			if (event.propertyName == "checked") {
				if (a.checked) {
					br.register(b);
				} else {
					br.unregister(b);
				}
			}
		});
		if (a.checked) {
			br.register(b);
		}
	});
	var bs = new A("enabled", function (a) {
		if (typeof a.disabled != "boolean") {
			return;
		}
		var b = arguments;
		IE7.CSS.addEventHandler(a, "onpropertychange", function () {
			if (event.propertyName == "disabled") {
				if (!a.isDisabled) {
					bs.register(b);
				} else {
					bs.unregister(b);
				}
			}
		});
		if (!a.isDisabled) {
			bs.register(b);
		}
	});
	var bt = new A("disabled", function (a) {
		if (typeof a.disabled != "boolean") {
			return;
		}
		var b = arguments;
		IE7.CSS.addEventHandler(a, "onpropertychange", function () {
			if (event.propertyName == "disabled") {
				if (a.isDisabled) {
					bt.register(b);
				} else {
					bt.unregister(b);
				}
			}
		});
		if (a.isDisabled) {
			bt.register(b);
		}
	});
	var bu = new A("indeterminate", function (a) {
		if (typeof a.indeterminate != "boolean") {
			return;
		}
		var b = arguments;
		IE7.CSS.addEventHandler(a, "onpropertychange", function () {
			if (event.propertyName == "indeterminate") {
				if (a.indeterminate) {
					bu.register(b);
				} else {
					bu.unregister(b);
				}
			}
		});
		IE7.CSS.addEventHandler(a, "onclick", function () {
			bu.unregister(b);
		});
	});
	var bv = new A("target", function (a) {
		var b = arguments;
		if (!a.tabIndex) {
			a.tabIndex = 0;
		}
		IE7.CSS.addEventHandler(document, "onpropertychange", function () {
			if (event.propertyName == "activeElement") {
				if (a.id && a.id == location.hash.slice(1)) {
					bv.register(b);
				} else {
					bv.unregister(b);
				}
			}
		});
		if (a.id && a.id == location.hash.slice(1)) {
			bv.register(b);
		}
	});
	var ct = /^attr/;
	var cu = /^url\s*\(\s*([^)]*)\)$/;
	var cv = {before0:"beforeBegin", before1:"afterBegin", after0:"afterEnd", after1:"beforeEnd"};
	var F = IE7.PseudoElement = o.extend({constructor:function (a, b, c) {
		this.position = b;
		var d = c.match(F.CONTENT), f, g;
		if (d) {
			d = d[1];
			f = d.split(/\s+/);
			for (var h = 0; (g = f[h]); h++) {
				f[h] = ct.test(g) ? {attr:g.slice(5, -1)} : (g.charAt(0) == "'") ? bB(g) : bg(g);
			}
			d = f;
		}
		this.content = d;
		this.base(a, bg(c));
	}, init:function () {
		this.match = x(this.selector);
		for (var a = 0; a < this.match.length; a++) {
			var b = this.match[a].runtimeStyle;
			if (!b[this.position]) {
				b[this.position] = {cssText:""};
			}
			b[this.position].cssText += ";" + this.cssText;
			if (this.content != null) {
				b[this.position].content = this.content;
			}
		}
	}, create:function (a) {
		var b = a.runtimeStyle[this.position];
		if (b) {
			var c = [].concat(b.content || "");
			for (var d = 0; d < c.length; d++) {
				if (typeof c[d] == "object") {
					c[d] = a.getAttribute(c[d].attr);
				}
			}
			c = c.join("");
			var f = c.match(cu);
			var g = "overflow:hidden;" + b.cssText.replace(/'/g, "\"");
			if (a.currentStyle.styleFloat != "none") {
			}
			var h = cv[this.position + Number(a.canHaveChildren)];
			var j = "ie7_pseudo" + F.count++;
			a.insertAdjacentHTML(h, i(F.ANON, this.className, j, g, f ? "" : c));
			if (f) {
				var q = document.getElementById(j);
				q.src = bB(f[1]);
				bo(q, "crop");
			}
			a.runtimeStyle[this.position] = null;
		}
	}, recalc:function () {
		if (this.content == null) {
			return;
		}
		for (var a = 0; a < this.match.length; a++) {
			this.create(this.match[a]);
		}
	}, toString:function () {
		return "." + this.className + "{display:inline}";
	}}, {CONTENT:/content\s*:\s*([^;]*)(;|$)/, ANON:"<ie7:! class='ie7_anon %1' id=%2 style='%3'>%4</ie7:!>", MATCH:/(.*):(before|after).*/, count:0});
	var cw = /^(submit|reset|button)$/;
	IE7.HTML.addRecalc("button,input", function (a) {
		if (a.tagName == "BUTTON") {
			var b = a.outerHTML.match(/ value="([^"]*)"/i);
			a.runtimeStyle.value = (b) ? b[1] : "";
		}
		if (a.type == "submit") {
			v(a, "onclick", function () {
				a.runtimeStyle.clicked = true;
				setTimeout("document.all." + a.uniqueID + ".runtimeStyle.clicked=false", 1);
			});
		}
	});
	IE7.HTML.addRecalc("form", function (c) {
		v(c, "onsubmit", function () {
			for (var a, b = 0; a = c[b]; b++) {
				if (cw.test(a.type) && !a.disabled && !a.runtimeStyle.clicked) {
					a.disabled = true;
					setTimeout("document.all." + a.uniqueID + ".disabled=false", 1);
				} else {
					if (a.tagName == "BUTTON" && a.type == "submit") {
						setTimeout("document.all." + a.uniqueID + ".value='" + a.value + "'", 1);
						a.value = a.runtimeStyle.value;
					}
				}
			}
		});
	});
	IE7.HTML.addRecalc("img", function (a) {
		if (a.alt && !a.title) {
			a.title = "";
		}
	});
	IE7.CSS.addRecalc("border-spacing", P, function (a) {
		if (a.currentStyle.borderCollapse != "collapse") {
			a.cellSpacing = D(a, a.currentStyle["border-spacing"]);
		}
	});
	IE7.CSS.addRecalc("box-sizing", "content-box", IE7.Layout.boxSizing);
	IE7.CSS.addRecalc("box-sizing", "border-box", IE7.Layout.borderBox);
	IE7.CSS.addFix(/opacity\s*:\s*([\d.]+)/, function (a, b) {
		return "zoom:1;filter:Alpha(opacity=" + ((b * 100) || 1) + ")";
	});
	var cx = /^image/i;
	IE7.HTML.addRecalc("object", function (a) {
		if (cx.test(a.type)) {
			a.body.style.cssText = "margin:0;padding:0;border:none;overflow:hidden";
			return a;
		}
	});
	IE7.loaded = true;
	(function () {
		try {
			bx.doScroll("left");
		}
		catch (e) {
			setTimeout(arguments.callee, 1);
			return;
		}
		try {
			eval(bO.innerHTML);
		}
		catch (e) {
		}
		bm = new RegExp(W(typeof IE7_PNG_SUFFIX == "string" ? IE7_PNG_SUFFIX : "-trans.png") + "$", "i");
		w = document.body;
		t = G ? w : bx;
		w.className += " ie7_body";
		bx.className += " ie7_html";
		if (G) {
			cq();
		}
		IE7.CSS.init();
		IE7.HTML.init();
		IE7.HTML.apply();
		IE7.CSS.apply();
		IE7.recalc();
	})();
})();

