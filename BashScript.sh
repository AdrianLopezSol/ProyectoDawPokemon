#!/bin/bash

# Nombre del archivo HTML de salida
output_file="changelog.html"

# Encabezado HTML
echo "<html><head><title>Changelog</title></head><body>" > $output_file
echo "<h1>Changelog</h1><table border='1'><thead><tr><th>Hash</th><th>Autor</th><th>Fecha</th><th>Mensaje</th></tr></thead><tbody>" >> $output_file

# Generar el changelog a partir del log de Git
git log --pretty=format:"<tr><td>%h</td><td>%an</td><td>%ar</td><td>%s</td></tr>" > temp_changelog.html

# Agregar el changelog al archivo HTML de salida
cat temp_changelog.html >> $output_file

# Limpiar archivos temporales
rm temp_changelog.html

# Cerrar etiquetas HTML
echo "</tbody></table></body></html>" >> $output_file

echo "Changelog generado en $output_file"