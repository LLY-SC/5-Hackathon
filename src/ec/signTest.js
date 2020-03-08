var ecdh = require('./ecdh.js');


// test generate private
var A = ecdh.generatePrivate();
var B = ecdh.generatePrivate();
var C = ecdh.generatePrivate();
var D = ecdh.generatePrivate();
A=A.toString();
B=B.toString();
C=C.toString();
D=D.toString();
console.log("generated A: ", A);
console.log("generated B: ", B);
console.log("generated C: ", C);
console.log("generated D: ", D);

var A1 = ecdh.generatePrivate(A.toString);

console.log("generated A1: ", A1);

// test private to public
var Ap = ecdh.generatePublic(A).toString();
var Bp = ecdh.generatePublic(B).toString();
var Cp = ecdh.generatePublic(C).toString();
var Dp = ecdh.generatePublic(D).toString();

console.log("generated APublic:  ", Ap, Ap.length);
console.log("generated BPublic:  ", Bp, Bp.length);
console.log("generated CPublic:  ", Cp, Cp.length);
console.log("generated DPublic:  ", Dp, Dp.length);


//test sign/verify
var hashA = ecdh.hashMessage("test message");

var sigA = ecdh.sign("test message", A);
console.log("geenerated A signed message with A private key", sigA);
console.log("test to object", sigA.toObject());
console.log("test to object", sigA.toString());


console.log("verifying use A public key", ecdh.verify(hashA, sigA, Ap));

