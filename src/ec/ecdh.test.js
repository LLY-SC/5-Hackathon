var ecdh = require('./ecdh.js');


// test generate private
var A = ecdh.generatePrivate();
var B = ecdh.generatePrivate();
A=A.toString();
B=B.toString();
console.log("generated A: ", A, A.length);
console.log("generated B: ", B, B.length);

// test private to public
var Ap = ecdh.generatePublic(A).toString();
var Bp = ecdh.generatePublic(B).toString();

console.log("generated APublic:  ", Ap, Ap.length);
console.log("generated BPublic:  ", Bp, Bp.length);


//test sign/verify
var hashA = ecdh.hashMessage("test message");

var sigA = ecdh.sign("test message", A);
console.log("geenerated A signed message with A private key", sigA);
console.log("test to object", sigA.toObject());
console.log("test to object", sigA.toString());


console.log("verifying use A public key", ecdh.verify(hashA, sigA, Ap));

//test en/de
var msg = "new secrest";
var CA = ecdh.generateCypher(A, Bp);
var CB = ecdh.generateCypher(B, Ap);

console.log("A secret cypher: ", CA);
console.log("B secret cypher: ", CB);

var enA = ecdh.encrypt(msg, CA);
var deA = ecdh.decrypt(enA, CB);


console.log("encrypted A side msg", enA);
console.log("decrypt the msg sent from A", deA);
//
