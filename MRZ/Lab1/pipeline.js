function pipeline(n, a, b, step){
    if (step == 0){
        var psum = multiply1Bin(a, b[0]);
        n = binarySum(n, psum);
        return n;
    }
    if (step == 1){
        n = shiftLeft(n);
        return n;
    }
    if (step == 2){
        var psum = multiply1Bin(a, b[1]);
        n = binarySum(n, psum);
        return n;
    }
    if (step == 3){
        n = shiftLeft(n);
        return n;
    }
    if (step == 4){
        var psum = multiply1Bin(a, b[2]);
        n = binarySum(n, psum);
        return n;
    }
    if (step == 5){
        n = shiftLeft(n);
        return n;
    } 
    if (step == 6){
        var psum = multiply1Bin(a, b[3]);
        n = binarySum(n, psum);
        return n;
    }
    return n;
}
