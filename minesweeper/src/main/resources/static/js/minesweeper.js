function reveal(x, y) {
    fetch(`/reveal?x=${x}&y=${y}`)
        .then(response => response.json())
        .then(data => {
            updateBoard(data);
        });
}

function flag(x, y) {
    fetch(`/flag?x=${x}&y=${y}`)
        .then(response => response.json())
        .then(data => {
            updateBoard(data);
        });
}

function updateBoard(board) {
    board.forEach(row => {
        row.forEach(cell => {
            let cellElement = document.getElementById(`cell-${cell.x}-${cell.y}`);
            cellElement.innerHTML = cell.display;
            cellElement.className = cell.revealed ? 'revealed' : '';
            cellElement.setAttribute('data-number', cell.number);
        });
    });

    // 남은 지뢰 개수 업데이트
    fetch("/remainingMines")
        .then(response => response.json())
        .then(data => {
            document.getElementById("mine-count").innerText = data;
        });
}
