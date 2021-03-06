function handleButtonClick() {
    var time = 0;
    if (!document.getElementById("inputT").value ||
        !document.getElementById("inputA").value ||
        !document.getElementById("inputB").value) {
        alert("Please fill in all the text fields!");
        return 0;
    }
    var arrA = document.getElementById("inputA").value;
    var arrB = document.getElementById("inputB").value;

    var t = document.getElementById("inputT").value;

    if (!inputIsNumber(t)){
        alert("Please input numbers in the fields!");
        return 0;
    } else {
        t = Number(t);
    }

    if (!inputIsArrayOfNumbers(arrA) || !inputIsArrayOfNumbers(arrB)){
        alert("Please input numbers in the fields!");
        return 0;
    }

    arrA = arrA.split(" ");
    arrB = arrB.split(" ");
    if (arrA.length != arrB.length) {
        alert("Please input equal number of elements");
        return 0;
    }
    var m = arrA.length;

    var table = document.getElementById("table");
    var rowCount = table.getElementsByTagName("tr").length;
    if (rowCount > 1) {
        for (var rowIndex = rowCount - 1; rowIndex > 0; rowIndex--) {
            table.deleteRow(rowIndex);
        }
    }
    
    for (var i = 0; i < arrA.length; i++) {
        if (!inputIsArrayOfNumbers(arrA[i])){
            alert("Please input numbers in the fields!");
            return 0;
        }
        arrA[i] = Number(arrA[i]);
        if (arrA[i] > 15 || arrA[i] < 0) {
            alert("Please input number in the [0; 15] interval!");
            return 0;
        }
        arrA[i] = printBinary(decimalToBinary(arrA[i]));
    }
    for (var i = 0; i < arrB.length; i++) {
        if (!inputIsArrayOfNumbers(arrB[i])){
            alert("Please input numbers in the fields!");
            return 0;
        }
        arrB[i] = Number(arrB[i]);
        if (arrB[i] > 15 || arrB[i] < 0) {
            alert("Please input number in the [0; 15] interval!");
            return 0;
        }
        arrB[i] = printBinary(decimalToBinary(arrB[i]));
    }

    for (var rowIndex = 1; rowIndex <= 7+m; rowIndex++) {
        i = rowIndex - 1;
        var row = table.insertRow();

        for (var cellIndex = 0; cellIndex < 10; cellIndex++) {
            row.insertCell(cellIndex);
        }
    }

    var boolArrA = [];
    var boolArrB = [];
    var arrC = [];

    for (var index = 0; index < m; index++) {
        boolArrA[index] = getBoolArray(arrA[index]);
        boolArrB[index] = getBoolArray(arrB[index]);
    }

    var res = [];
    var resMult = [];
    var previousRes = [];
    var cellIndex = 0;
    var rowIndex = 0;
    var step = [];
    var time = 0;
    for (var i = 0; i < m; i++){
        step[i] = 0;
        res[i] = [];
        resMult = [];
        previousRes[i] = [];
    }

    while (step[m-1] < 9){
        time+=t;

        for (var j = m - 1; j >= 0; j--){
            if (step[j] < 9){
                cellIndex = step[j]+1;
                previousRes[j] = printBinaryRes(resToString(res[j]));
                res[j] = pipeline(res[j], boolArrA[j], boolArrB[j], step[j], 1);
                resMult[j] = pipeline(res[j], boolArrA[j], boolArrB[j], step[j], 2);
                rowIndex = j + step[j] + 1;

                if (step[j] == 8){
                    rowIndex--;
                    var resCell = table.rows[rowIndex].cells[cellIndex];
                    resCell.innerHTML = "<b>A:</b> "+binaryToDecimal(resToString(boolArrA[j]))+
                        "<br>"+"<b>B:</b> "+binaryToDecimal(resToString(boolArrB[j]))+
                        "<br>"+"<b>Result: </b>"+binaryToDecimal(resToString(res[j]));
                } else {
                    var resCell = table.rows[rowIndex].cells[cellIndex];
                    if(step[j]%2 == 0){
                        resCell.innerHTML = "<b>A:</b> "+printBinary(resToString(boolArrA[j]))+
                        "<br>"+"<b>B:</b> "+printBinary(resToString(boolArrB[j]))+
                        "<br>"+
                        "<b>Previous result: </b>"+previousRes[j]+"<br>"+
                        "<b>Result: </b>"+printBinaryRes(resToString(res[j]));
                    } else{
                        resCell.innerHTML = "<b>A:</b> "+printBinary(resToString(boolArrA[j]))+
                        "<br>"+"<b>B:</b> "+printBinary(resToString(boolArrB[j]))+
                        "<br>"+
                        "<b>Previous result: </b>"+previousRes[j]+"<br>"+
                        "<b>Result multiply: </b>" + printBinaryRes(resToString(resMult[j]))+"<br>"+
                        "<b>Result: </b>"+printBinaryRes(resToString(res[j]));
                    }
                }
                step[j]++;
            }
        }
    }
    var timeOutput = 0;
    for (var rowIndex = 1; rowIndex <= 7+m; rowIndex++) {
        var resCell = table.rows[rowIndex].cells[0];
        timeOutput +=t;
        resCell.innerHTML += "<br><b>Time:</b> "+timeOutput;
    }

    for (var i = 0; i < res.length; i++){
        arrC[i] = binaryToDecimal(resToString(res[i]));
    }

    document.getElementById("result").value = arrC;
}