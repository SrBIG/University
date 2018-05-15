function binaryToDecimal(n) {
    n = parseInt(n, 2);
    return n;
}

function decimalToBinary(n) {
    n = Number(n);
    n = (n >>> 0).toString(2);
    return n;
}

function printBinary(n) {
    var str = String(n);
    if (str.length % 4) {
        if (str.length % 4 === 1) {
            str = "000" + str;
        }
        if (str.length % 4 === 2) {
            str = "00" + str;
        }
        if (str.length % 4 === 3) {
            str = "0" + str;
        }

    }
    str = str.replace(/(\d)(?=(\d\d\d\d)+([^\d]|$))/g, '$1 ');

    if (( /(?=(0000\s)+([\d]|$))/).test(str)){
        str = str.substring(5);
    }
    return str;
}

function printBinaryRes(str){
    if ((str.length % 4)){
        if (str.length % 4 === 1) {
        return("000" + str.substring(0, 1) + " " + str.substring(1));
    }
        if (str.length % 4 === 2) {
            return("00" + str.substring(0, 2) + " " + str.substring(2));
        }
        if (str.length % 4 === 3) {
            return("0" + str.substring(0, 3) + " " + str.substring(3));
        }
    }
    return ("0000 "+str);
}

function inputIsArrayOfNumbers(str){
    return (( /(^[\d\s]+$)/.test(str)));
}

function inputIsNumber(str){
    return (( /(^[\d]+$)/.test(str)));
}

function getBoolArray(str){
    var arr = String(str);
    arr = str.split("");
    for (var i = 0; i < arr.length; i++){
        arr[i] = Number(arr[i]); // т.к. строка всегда true
        arr[i] = Boolean(arr[i]);
    }
    return arr;
}

function multiply1Bin(a, b){
    var c = [];
    for (var i = 0; i < a.length; i++){
        if (b == 1){
            if (a[i] == 0){
                c[i] = 0;
            } else {
                c[i] = 1;
            }
        }
        if (b == 0){
            c[i] = 0;
        }
    }
    return c;
}

function shiftLeft(c){
    c.push(0);
    return c;
}

function binarySum(a, b){
    if (a.length > b.length){
        var dif = a.length - b.length;
        for (var i = 0; i < dif; i++){
            b.unshift(false);
        }
    }
    if (b.length > a.length){
        var dif = b.length - a.length;
        for (var i = 0; i < dif; i++){
            a.unshift(false);
        }
    }

    var c = [];
    var d = 0;

    for (var i = a.length - 1; i >= 0; i--) {
        if (a[i] == 0 && b[i] == 0) {
            if (d == 1){
                c[i] = 1;
                d = 0;
            } else {
                c[i] = 0;
                d = 0;
            }
        } else {
            if ((a[i] == 1 && b[i] == 0) || (a[i] == 0 && b[i] == 1)) {
                if (d == 1) {
                    c[i] = 0;
                    d = 1;
                } else {
                    c[i] = 1;
                    d = 0;
                }
            } else {
                if (a[i] == 1 && b[i] == 1) {
                    if (d == 1) {
                        c[i] = 1;
                        d = 1;
                    } else {
                        c[i] = 0;
                        d = 1;
                    }
                }
            }
        }
    }

    if (d == 1){
        c.unshift(1);
    }

    return c;
}

function resToString(c){
    for (var i = 0; i < c.length; i++){
        c[i] = Number(c[i]);
        c[i] = String(c[i]);
    }
    c = c.join("");
    //alert("c: "+c);
    return c;
}

function binaryMultiply(a,b){
    var partSum = [];
    var res = [];
    for (var i = 0; i < b.length; i++){
        res = shiftLeft(res);
        partSum = multiply1Bin(a, b[i]);
        res = binarySum(res, partSum);
    }
    return res;
}
