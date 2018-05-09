function pipeline(n, a, b, step, st){
    if (step == 0 && st == 1){
        var psum = multiply1Bin(a, b[0]);
        n = binarySum(n, psum);
        return n;
    }
    if (step == 0 && st == 2){
        var psum = multiply1Bin(a, b[0]);
        return psum;
    }

    if (step == 1 && st == 1){
        n = shiftLeft(n);
        return n;
    }

    if (step == 2 && st == 1){
        var psum = multiply1Bin(a, b[1]);
        n = binarySum(n, psum);
        return n;
    }
    if (step == 2 && st == 2){
        var psum = multiply1Bin(a, b[1]);
        return psum;
    }

    if (step == 3 && st == 1){
        n = shiftLeft(n);
        return n;
    }

    if (step == 4 && st == 1){
        var psum = multiply1Bin(a, b[2]);
        n = binarySum(n, psum);
        return n;
    }
    if (step == 4 && st == 2){
        var psum = multiply1Bin(a, b[2]);
        return psum;
    }

    if (step == 5 && st == 1){
        n = shiftLeft(n);
        return n;
    } 

    if (step == 6 && st == 1){
        var psum = multiply1Bin(a, b[3]);
        n = binarySum(n, psum);
        return n;
    }
    if (step == 6 && st == 2){
        var psum = multiply1Bin(a, b[3]);
        return psum;
    }
    return n;
}