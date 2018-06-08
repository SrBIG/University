var A = [];
var B = [];
var G = [];
var H = [];
var D = [];
var C = [];
var p = 0;
var m = 0;
var q = 0
var n = 0;
var Knr = 0;
var Enr = 0;
var Dnr = 0;
var L = 0;
var sumTime = 0;
var compositionTime = 0;
var comparisonTime = 0;
var tableGenerated = 0;
var compositionAmount = 0;
var sumAmount = 0;
var comparisonAmount = 0;
var T1 = 0;
var Tn = 0;
var compareRange = 0;
var totalRang = 0;
var aftComma = 5;

//created by: Biruchov
function start() {
    p = document.getElementById('pNum').value;
    m = document.getElementById('mNum').value;
    q = document.getElementById('qNum').value;
    n = document.getElementById('nNum').value;
    
    sumTime = document.getElementById('sumTime').value;
    compositionTime = document.getElementById('compositTime').value;
    comparisonTime = document.getElementById('compareTime').value;

    T1 = 0;
    Tn = 0;
    Knr = 0;
    Enr = 0;
    Dnr = 0;
    L = 0;
    totalRang = 2 * m * p * q;

    if (rightInput() == false)
        return;
        
    generateData();

    cMatrix();
    calculateEnr();
    calculateD();

    if (tableGenerated == 1) {
        var table = document.getElementById('mainTable');

        document.body.removeChild(table);
        tableGenerated = 0;

        var table = document.createElement("table");
        table.id = 'mainTable';
        table.border = '1';
        document.body.appendChild(table);
    }

    generateTable();
}

//created by: Biruchov
function rightInput() {
    if (p == "" || m == "" || q == "" || n == "" || sumTime == "" || comparisonTime == "" || compositionTime == "") {
        alert("Заполните все поля.");
        return false;
    }

   if (!p.match(/^\d+$/) || !m.match(/^\d+$/) || !q.match(/^\d+$/) || !n.match(/^\d+$/) || !sumTime.match(/^\d+$/) || !comparisonTime.match(/^\d+$/) 
   || !compositionTime.match(/^\d+$/) || /^0/.test(p) || /^0/.test(m) || /^0/.test(q) || /^0/.test(n) || /^0/.test(compositionTime) 
   || /^0/.test(compositionTime) || /^0/.test(sumTime)) {
        alert("Введите корректные значения.");
        return false;
    }

    sumTime = parseInt(sumTime);
    comparisonTime = parseInt(comparisonTime);
    compositionTime = parseInt(compositionTime);

    return true;
}

//created by: Biruchov
function generateData() {
    for (var i = 0; i < p; i++) {
        A[i] = new Array();
        for (var j = 0; j < m; j++) {
            A[i][j] = (2 * Math.random() - 1).toFixed(aftComma);
        }
    }

    for (var i = 0; i < m; i++) {
        B[i] = new Array();
        for (var j = 0; j < q; j++) {
            B[i][j] = (2 * Math.random() - 1).toFixed(aftComma);
        }
    }

    for (var i = 0; i < m; i++) {
        G[i] = new Array();
        for (var j = 0; j < p; j++) {
            G[i][j] = (2 * Math.random() - 1).toFixed(aftComma);
        }
    }
    
    for (var i = 0; i < q; i++) {
        H[i] = new Array();
        for (var j = 0; j < m; j++) {
            H[i][j] = (2 * Math.random() - 1).toFixed(aftComma);
        }
    }
}

//created by: Biruchov
function dMatrix() {
    D = [];
    for(var i = 0; i < p; i++) {
        D[i] = new Array();
        for (var j = 0; j < q; j++) {
            D[i][j] = new Array();
            for (var k = 0; k < m; k++) {
                D[i][j][k] = (A[i][k] * B[k][j]).toFixed(aftComma);

                L += 2 * compositionTime;
                T1 += compositionTime;
                compositionAmount++;
            }
        }
    }
    Tn += Math.ceil(compositionAmount / n) * compositionTime;
    compositionAmount = 0;
}

//created by: Biruchov
function cMatrix() {
    compositionAmount = 0;
    sumAmount = 0;
    comparisonAmount = 0;

    dMatrix();
    for(var i = 0; i < p; i++) {
        C[i] = new Array();
        for (var j = 0; j < q; j++) {
            C[i][j] = calculateCij(i, j);
        }
    }
    Tn += Math.ceil(comparisonAmount / n) * comparisonTime;
}

//created by: Biruchov
function calculateCij(i, j) {
    var Cij = 0;
    var Dk = [];
    for (var k = 0; k < D.length; k++) {
        Dk.push(parseFloat(D[k][i][j]));
    }
    Cij = compare(i, j, Dk);
    compareRange = 0;
    return Cij.toFixed(aftComma);
}

//created by: Biruchov
function compare(i, j, Dk) {
    for(var x = 0; x < m; x++) {
        T1 += comparisonTime;
        comparisonAmount++;
        L += 2 * comparisonTime;
        compareRange += 2;      
        if(G[x][i] < H[j][x]) {
            return composeArray(Dk, 2);
        }
    }
    return sumArray(Dk, 2);
}

//created by: Vasilyeva
function sumArray(D, r) {
    
    if (D.length == 1) {
        var res = D[0];
        return res;
    } else {
        var t = 0;
        var nIter = 0;
        var isEven = 1;
        var res = [];
        if (D.length % 2 === 1) {
            res.push(D[D.length - 1]);
            isEven = 0;
        }
        for (var i = 0; i < (D.length - D.length % 2); i += 2) {
            if (nIter === n) {
                for (var j = i; j < D.length; j++) {
                    res.push(D[j]);
                }
                break;
            }

            res.push(D[i] + D[i + 1]);
            sumAmount++;
            nIter += 1;
            t += sumTime;

        }
        if(isEven == 1)
          L += sumTime * sumAmount * r;
        else
            L += sumTime * sumAmount * r - 1;
        T1 += t;
        Tn += Math.ceil(t / n);
        return (sumArray(res, r * 2));
    }
}

//created by: Biruchov
function composeArray(D, range) {
    if (D.length == 1) {
        var res = D[0];
        return res;
    } else {
        var t = 0;
        var nIter = 0;
        var res = [];
        if (D.length % 2 === 1) {
            res.push(D[D.length - 1]);
        }
        for (var i = 0; i < (D.length - D.length % 2); i += 2) {
            if (nIter === n) {
                for (var j = i; j < D.length; j++) {
                    res.push(D[j]);
                }
                break;
            }

            res.push(D[i] * D[i + 1]);

            nIter += 1;
            compositionAmount++;
            t += compositionTime;
        }
        T1 += t;
        Tn += Math.ceil(t / n);
        L += compositionTime * compositionAmount * range;
        return (composeArray(res, range * 2));
    }
}

//created by: Biruchov
function calculateKnr() {
    Knr = T1 / Tn;
}

//created by: Biruchov
function calculateEnr() {
    calculateKnr();
    Enr = Knr / n;
}

//created by: Biruchov
function calculateD() {
    L = L / totalRang;
    Dnr = Tn / L;
}

//created by: Biruchov
function generateTable() {
    tableGenerated = 1;

    var table = document.getElementById('mainTable');
    var tableRow = document.createElement('tr');

    var matrix;
    var matrixRow;
    var matrixCell;
    var matrixTitle;

    var tableCell = document.createElement('td');
    matrix = document.createElement('table');
    tableCell.innerHTML = "<div class = 'nameMatrix'>A:</div>";

    for (var i = 0; i < p; i++) {
        matrixRow = document.createElement('tr');
        for (var j = 0; j < m; j++) {
            matrixCell = document.createElement('td');
            matrixCell.innerHTML = A[i][j];
            matrixRow.appendChild(matrixCell);
        }
        matrix.appendChild(matrixRow);
    }

    tableCell.appendChild(matrix);
    tableRow.appendChild(tableCell);
    table.appendChild(tableRow);
    
    tableRow = document.createElement('tr');

    tableCell = document.createElement('td');
    matrix = document.createElement('table');
    tableCell.innerHTML = "<div class = 'nameMatrix'>B:</div>";

    for (var i = 0; i < m; i++) {
        matrixRow = document.createElement('tr');
        for (var j = 0; j < q; j++) {
            matrixCell = document.createElement('td');
            matrixCell.innerHTML = B[i][j];
            matrixRow.appendChild(matrixCell);
        }
        matrix.appendChild(matrixRow);
    }
    tableCell.appendChild(matrix);
    tableRow.appendChild(tableCell);
    table.appendChild(tableRow);

    tableRow = document.createElement('tr');

    tableCell = document.createElement('td');
    matrix = document.createElement('table');
    tableCell.innerHTML = "<div class = 'nameMatrix'>G:</div>";

    for (var i = 0; i < m; i++) {
        matrixRow = document.createElement('tr');
        for (var j = 0; j < p; j++) {
            matrixCell = document.createElement('td');
            matrixCell.innerHTML = G[i][j];
            matrixRow.appendChild(matrixCell);
        }
        matrix.appendChild(matrixRow);
    }

    tableCell.appendChild(matrix);
    tableRow.appendChild(tableCell);
    table.appendChild(tableRow);
    
    tableRow = document.createElement('tr');

    tableCell = document.createElement('td');
    matrix = document.createElement('table');
    tableCell.innerHTML = "<div class = 'nameMatrix'>H:</div>";
  
    for (var i = 0; i < q; i++) {
        matrixRow = document.createElement('tr');
        for (var j = 0; j < m; j++) {
            matrixCell = document.createElement('td');
            matrixCell.innerHTML = H[i][j];
            matrixRow.appendChild(matrixCell);
        }
        matrix.appendChild(matrixRow);
    }
    tableCell.appendChild(matrix);
    tableRow.appendChild(tableCell);
    table.appendChild(tableRow);

    tableRow = document.createElement('tr');

    tableCell = document.createElement('td');
        matrix = document.createElement('table');
        tableCell.innerHTML = "<div class = 'nameMatrix'>C:</div>";
        for (var i = 0; i < p; i++) {
            matrixRow = document.createElement('tr');
            for (var j = 0; j < q; j++) {
                matrixCell = document.createElement('td');
                matrixCell.innerHTML = C[i][j];
                matrixRow.appendChild(matrixCell);
            }
            matrix.appendChild(matrixRow);
        }
        tableCell.appendChild(matrix);
        tableRow.appendChild(tableCell);
        table.appendChild(tableRow);
    
        tableRow = document.createElement('tr');

        tableCell = document.createElement('td');
        tableCell.style.textAlign = 'left';
        tableCell.innerHTML = 
            "<b>Tn:</b> " + Tn + "<br>" +
            "<b>T1:</b> " + T1 + "<br>" +
            "<b>Ky(n,r):</b> " + Knr.toFixed(aftComma) + "<br>" +
            "<b>e(n,r):</b>  " + Enr.toFixed(aftComma) + "<br>" +
            "<b>D:</b> " + Dnr.toFixed(aftComma) + "<br>" +
            "<b>r:</b> " + totalRang;
        tableRow.appendChild(tableCell);
        table.appendChild(tableRow);

        document.body.appendChild(table);
}

//////////// created by: Vasilyeva /////////////////
////////////////////charts////////////////////////
function buildCharts() {
    A = [];
    B = [];
    C = [];
    G = [];
    H = [];

    Tn = 0;
    Knr = 0;
    Enr = 0;
    Dnr = 0;

    p = parseInt(document.getElementById('pNum').value);
    m = parseInt(document.getElementById('mNum').value);
    q = parseInt(document.getElementById('qNum').value);
    n = parseInt(document.getElementById('nNum').value);
    
    sumTime = parseInt(document.getElementById('sumTime').value);
    compositionTime = parseInt(document.getElementById('compositTime').value);
    comparisonTime = parseInt(document.getElementById('compareTime').value);

    T1 = 1;
    document.body.innerHTML = "<div id=\"chart1\"></div>";
    document.body.innerHTML += "<div id=\"chart2\"></div>";
    document.body.innerHTML += "<div id=\"chart3\"></div>";
    document.body.innerHTML += "<div id=\"chart4\"></div>";
    document.body.innerHTML += "<div id=\"chart5\"></div>";
    document.body.innerHTML += "<div id=\"chart6\"></div>";

    google.charts.load('current', {'packages': ['line']});
    google.charts.setOnLoadCallback(drawChart);

    function drawChart() {

        var benchmark = [];


        var RBenchmark = [];
        for (m = 1; m < 21; m++) {
            RBenchmark[m - 1] = new Array();
            generateData();
            RBenchmark[m - 1].push(A);
            RBenchmark[m - 1].push(B);
        }


        for (n = 1; n < 71; n++) {
            benchmark[n - 1] = new Array();
            benchmark[n - 1].push(n);
            for (m = 1; m < 21; m++) {
                A = RBenchmark[m - 1][0];
                B = RBenchmark[m - 1][1];
                C = [];
                Tn = 0;
                T1 = 0;
                Knr = 0;
                Enr = 0;
                Dnr = 0;
                cMatrix();
                calculateEnr();
                benchmark[n - 1].push(Knr);
            }
        }


        var data = new google.visualization.DataTable();
        data.addColumn('number', 'n');
        data.addColumn('number', '1');
        data.addColumn('number', '2');
        data.addColumn('number', '3');
        data.addColumn('number', '4');
        data.addColumn('number', '5');
        data.addColumn('number', '6');
        data.addColumn('number', '7');
        data.addColumn('number', '8');
        data.addColumn('number', '9');
        data.addColumn('number', '10');
        data.addColumn('number', '11');
        data.addColumn('number', '12');
        data.addColumn('number', '13');
        data.addColumn('number', '14');
        data.addColumn('number', '15');
        data.addColumn('number', '16');
        data.addColumn('number', '17');
        data.addColumn('number', '18');
        data.addColumn('number', '19');
        data.addColumn('number', '20');

        data.addRows(benchmark);

        var options = {
            vAxis: {
                title: 'K(n,r)'
            },
            width: 1400,
            height: 800

        };

        var chart = new google.charts.Line(document.getElementById('chart1'));

        chart.draw(data, google.charts.Line.convertOptions(options));

        benchmark = [];
        m = 31;
        A = [];
        B = [];
        G = [];
        H = [];
        generateData();

        for (m = 1; m < 31; m++) {
            benchmark[m - 1] = new Array();
            benchmark[m - 1].push(m);
            for (n = 1; n < 21; n++) {
                C = [];
                Tn = 0;
                T1 = 0;
                Knr = 0;
                Enr = 0;
                Dnr = 0;
                cMatrix();
                calculateEnr();
                benchmark[m - 1].push(Knr);
            }
        }

        data = new google.visualization.DataTable();
        data.addColumn('number', 'r');
        data.addColumn('number', '1');
        data.addColumn('number', '2');
        data.addColumn('number', '3');
        data.addColumn('number', '4');
        data.addColumn('number', '5');
        data.addColumn('number', '6');
        data.addColumn('number', '7');
        data.addColumn('number', '8');
        data.addColumn('number', '9');
        data.addColumn('number', '10');
        data.addColumn('number', '11');
        data.addColumn('number', '12');
        data.addColumn('number', '13');
        data.addColumn('number', '14');
        data.addColumn('number', '15');
        data.addColumn('number', '16');
        data.addColumn('number', '17');
        data.addColumn('number', '18');
        data.addColumn('number', '19');
        data.addColumn('number', '20');

        data.addRows(benchmark);

        options = {
            vAxis: {
                title: 'K(n,r)'
            },
            width: 1347,
            height: 800

        };

        chart = new google.charts.Line(document.getElementById('chart2'));
        chart.draw(data, google.charts.Line.convertOptions(options));

        benchmark = [];
        m = 31;
        A = [];
        B = [];
        G = [];
        H = [];
        generateData();

        for (m = 1; m < 31; m++) {
            benchmark[m - 1] = new Array();
            benchmark[m - 1].push(m);
            for (n = 1; n < 21; n++) {
                C = [];

                Tn = 0;
                T1 = 0;
                Knr = 0;
                Enr = 0;
                Dnr = 0;
                cMatrix();
                calculateEnr();
                benchmark[m - 1].push(Enr);
            }
        }

        data = new google.visualization.DataTable();
        data.addColumn('number', 'r');
        data.addColumn('number', '1');
        data.addColumn('number', '2');
        data.addColumn('number', '3');
        data.addColumn('number', '4');
        data.addColumn('number', '5');
        data.addColumn('number', '6');
        data.addColumn('number', '7');
        data.addColumn('number', '8');
        data.addColumn('number', '9');
        data.addColumn('number', '10');
        data.addColumn('number', '11');
        data.addColumn('number', '12');
        data.addColumn('number', '13');
        data.addColumn('number', '14');
        data.addColumn('number', '15');
        data.addColumn('number', '16');
        data.addColumn('number', '17');
        data.addColumn('number', '18');
        data.addColumn('number', '19');
        data.addColumn('number', '20');

        data.addRows(benchmark);

        options = {
            vAxis: {
                title: 'E(n,r)'
            },
            width: 1347,
            height: 800

        };

        chart = new google.charts.Line(document.getElementById('chart3'));
        chart.draw(data, google.charts.Line.convertOptions(options));

        benchmark = [];
        for (n = 1; n < 31; n++) {
            benchmark[n - 1] = new Array();
            benchmark[n - 1].push(n);
            for (m = 1; m < 21; m++) {
                A = RBenchmark[m - 1][0];
                B = RBenchmark[m - 1][1];
                C = [];

                Tn = 0;
                T1 = 0;
                Knr = 0;
                Enr = 0;
                Dnr = 0;
                cMatrix();
                calculateEnr();
                benchmark[n - 1].push(Enr);
            }
        }

        data = new google.visualization.DataTable();
        data.addColumn('number', 'n');
        data.addColumn('number', '1');
        data.addColumn('number', '2');
        data.addColumn('number', '3');
        data.addColumn('number', '4');
        data.addColumn('number', '5');
        data.addColumn('number', '6');
        data.addColumn('number', '7');
        data.addColumn('number', '8');
        data.addColumn('number', '9');
        data.addColumn('number', '10');
        data.addColumn('number', '11');
        data.addColumn('number', '12');
        data.addColumn('number', '13');
        data.addColumn('number', '14');
        data.addColumn('number', '15');
        data.addColumn('number', '16');
        data.addColumn('number', '17');
        data.addColumn('number', '18');
        data.addColumn('number', '19');
        data.addColumn('number', '20');

        data.addRows(benchmark);

        options = {
            vAxis: {
                title: 'E(n,r)'
            },
            width: 1347,
            height: 800

        };

        chart = new google.charts.Line(document.getElementById('chart4'));
        chart.draw(data, google.charts.Line.convertOptions(options));

        benchmark = [];
        m = 31;
        A = [];
        B = [];
        G = [];
        H = [];
        generateData();
        for (m = 1; m < 21; m++) {
            benchmark[m - 1] = new Array();
            benchmark[m - 1].push(m);
            for (n = 1; n < 21; n++) {
                C = [];
                L = 0;
                Tn = 0;
                T1 = 0;
                Knr = 0;
                Enr = 0;
                Dnr = 0;
                totalRang = 2 * m * p * q;;
                cMatrix();
                calculateD();
                benchmark[m - 1].push(Dnr);
            }
        }

        data = new google.visualization.DataTable();
        data.addColumn('number', 'r');
        data.addColumn('number', '1');
        data.addColumn('number', '2');
        data.addColumn('number', '3');
        data.addColumn('number', '4');
        data.addColumn('number', '5');
        data.addColumn('number', '6');
        data.addColumn('number', '7');
        data.addColumn('number', '8');
        data.addColumn('number', '9');
        data.addColumn('number', '10');
        data.addColumn('number', '11');
        data.addColumn('number', '12');
        data.addColumn('number', '13');
        data.addColumn('number', '14');
        data.addColumn('number', '15');
        data.addColumn('number', '16');
        data.addColumn('number', '17');
        data.addColumn('number', '18');
        data.addColumn('number', '19');
        data.addColumn('number', '20');

        data.addRows(benchmark);

        options = {
            vAxis: {
                title: 'D(n,r)'
            },
            width: 1347,
            height: 800

        };

        chart = new google.charts.Line(document.getElementById('chart5'));

        chart.draw(data, google.charts.Line.convertOptions(options));
        benchmark = [];
        for (n = 1; n < 51; n++) {
            benchmark[n - 1] = new Array();
            benchmark[n - 1].push(n);
            for (m = 1; m < 21; m++) {
                A = RBenchmark[m - 1][0];
                B = RBenchmark[m - 1][1];
                C = [];

                L = 0;
                Tn = 0;
                T1 = 0;
                Knr = 0;
                Enr = 0;
                Dnr = 0;
                //totalRang = m * p * q;
                cMatrix();
                calculateD();
                benchmark[n - 1].push(Dnr);
            }
        }

        data = new google.visualization.DataTable();
        data.addColumn('number', 'n');
        data.addColumn('number', '1');
        data.addColumn('number', '2');
        data.addColumn('number', '3');
        data.addColumn('number', '4');
        data.addColumn('number', '5');
        data.addColumn('number', '6');
        data.addColumn('number', '7');
        data.addColumn('number', '8');
        data.addColumn('number', '9');
        data.addColumn('number', '10');
        data.addColumn('number', '11');
        data.addColumn('number', '12');
        data.addColumn('number', '13');
        data.addColumn('number', '14');
        data.addColumn('number', '15');
        data.addColumn('number', '16');
        data.addColumn('number', '17');
        data.addColumn('number', '18');
        data.addColumn('number', '19');
        data.addColumn('number', '20');

        data.addRows(benchmark);

        options = {
            vAxis: {
                title: 'D(n,r)'
            },
            width: 1347,
            height: 800

        };

        chart = new google.charts.Line(document.getElementById('chart6'));
        chart.draw(data, google.charts.Line.convertOptions(options));

    }
} 
