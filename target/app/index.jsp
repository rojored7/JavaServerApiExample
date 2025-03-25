<!DOCTYPE html>
<html>
<head>
    <title>Word Management</title>
</head>
<body>
    <h1>Manage Words</h1>

    <h2>Add a Word</h2>
    <form action="words" method="post">
        <input type="text" name="word" placeholder="Enter word" required />
        <button type="submit">Add Word</button>
    </form>

    <h2>View Words</h2>
    <form action="words" method="get">
        <button type="submit">Show All Words</button>
    </form>

    <h2>Delete a Word</h2>
    <form action="words" method="delete">
        <input type="text" name="word" placeholder="Enter word to delete" required />
        <button type="submit">Delete Word</button>
    </form>

    <h1>Obtener el clima de una ciudad</h1>
    <form action="words" method="get">
        <input type="text" name="city" placeholder="Ingresa una ciudad" />
        <button type="submit">Mostrar clima y palabras guardadas</button>
    </form>
</body>
</html>
