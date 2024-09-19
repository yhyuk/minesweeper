function revealCell(x, y) {
    fetch('/reveal', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded',
        },
        body: new URLSearchParams({
            x: x,
            y: y
        })
    })
    .then(response => response.text())
    .then(html => {
        // 서버에서 반환된 HTML을 현재 페이지에 적용
        document.open();
        document.write(html);
        document.close();
    })
    .catch(error => {
        console.error('Error:', error);
    });
}
