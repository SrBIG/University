function pipeline(n, a, b, step){
    if (step == 0){
        var psum = multiply1Bin(a, b[0]);
        var res = {
            resMultiply: [],
            resSumm: binarySum(n, psum)
        };
        return res;
    }
    if (step == 1){
        var res = {
            resMultiply: [],
            resSumm: shiftLeft(n)
        };
        return res;
    }
    if (step == 2){
        var psum = multiply1Bin(a, b[1]);
        var res = {
            resMultiply: [],
            resSumm: binarySum(n, psum)
        };       
        return res;
    }
    if (step == 3){
        var res = {
            resMultiply: [],
            resSumm: shiftLeft(n)
        };
        return res;
    }
    if (step == 4){
        var psum = multiply1Bin(a, b[2]);
        var res = {
            resMultiply: [],
            resSumm: binarySum(n, psum)
        };
        return res;
    }
    if (step == 5){
        var res = {
            resMultiply: [],
            resSumm: shiftLeft(n)
        };
        return res;
    } 
    if (step == 6){
        var psum = multiply1Bin(a, b[3]);
        var res = {
            resMultiply: [],
            resSumm: binarySum(n, psum)
        };
        return res;
    }
    var res = {
            resMultiply: [],
            resSumm: n
        };
    return res;
}
