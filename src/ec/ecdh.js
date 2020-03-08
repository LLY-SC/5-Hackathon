/************************
*使用以太钱包生成公私钥
*进行加签验签
***************** */
let ethUtil = require('ethereumjs-util');
var bitcore = require('bitcore-lib');
var AESCBC = require('./node_modules/bitcore-ecies/lib/aescbc');
var PrivateKey = bitcore.PrivateKey;
var PublicKey = bitcore.PublicKey;
var createKeccakHash = require('keccak')
var secp256k1 = require('secp256k1')

function defineImmutable(target, values) {
    Object.keys(values).forEach(function(key){
      Object.defineProperty(target, key, {
        configurable: false,
        enumerable: true,
        value: values[key]
      });
    });
    return target;
  }

exports.generatePrivate = function(private) {
	return private? PrivateKey(private):PrivateKey();
}

exports.generatePublic = function(privateKey) {
	return privateKey.publicKey? privateKey.publicKey: exports.generatePrivate(privateKey).publicKey;
}

exports.generateKeyPair = function() {
	var newPrivate = PrivateKey();
	var keyPair = {
		privateKey: newPrivate.toString(),
		publicKey: newPrivate.publicKey.toString()
	};
	return keyPair;
}

exports.hashMessage = function (msg) {
	return _sha3(msg);
}

var _sha3 = function (a, bits) {
	if (!Buffer.isBuffer(a)) {
		a = Buffer(a);
	}
  	if (!bits) bits = 256
  	return createKeccakHash('keccak' + bits).update(a).digest()
}

exports.sign = function (msg, privateKey) {
	if (!Buffer.isBuffer(privateKey)) {
		privateKey = exports.generatePrivate(privateKey).toBuffer();
	}
  var sig = secp256k1.sign(exports.hashMessage(msg), privateKey)
  var fullRet = {}
  fullRet.r = sig.signature.slice(0, 32).toString('hex')
  fullRet.s = sig.signature.slice(32, 64).toString('hex')
  fullRet.v = sig.recovery + 27
  return {
  	toBuffer: function() {
  		return sig.signature;
  	},
  	toObject: function() {
  		return fullRet;
  	},
  	toString: function() {
  		return sig.signature.toString('hex')
  	}
  }
}

exports.verify = function(msgHash, signature, publicKey) {
	if (!Buffer.isBuffer(publicKey)) {
		publicKey = PublicKey.fromString(publicKey).toBuffer();
	}
	return secp256k1.verify(msgHash, signature.toBuffer(), publicKey);
}

exports.generateCypher = function(AprivateKey, BpublicKey) {
	var bPublic = PublicKey.fromString(BpublicKey).toBuffer();
	var aPrivate = exports.generatePrivate(AprivateKey).toBuffer();
	// var ap 
	var cypher = secp256k1.ecdh(bPublic,aPrivate);
	return cypher
}

exports.encrypt = function(data, cypher) {
	var ivbuf = _sha3(data).slice(0,16);
	if (!Buffer.isBuffer(data)){
		data = Buffer(data);
	}
	var encryptedAES = AESCBC.encryptCipherkey(data, cypher,ivbuf);
	return encryptedAES
}

exports.decrypt = function(encryptedData, cypher) {
	var decryptedAES = AESCBC.decryptCipherkey(encryptedData, cypher);
	if (Buffer.isBuffer(decryptedAES)){
		decryptedAES = decryptedAES.toString();
	}
	return decryptedAES;
}
