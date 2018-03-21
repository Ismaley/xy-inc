#!/usr/bin/env bash

if [ $# -eq 0 ]; then
    echo "Nenhum parâmetro informado! Informar nome do arquivo!"
else
    ARGS=$1
    echo "O arquivo $ARGS.xml será criado!"
    DATE=`date +%Y%m%d%H%M`
    USER=`whoami`
    FILENAME=$DATE-$ARGS.xml
    CHANGELOG_ROOT=xy-locator-repository/src/main/resources/db/changelog
    FOLDER_FILENAME=$CHANGELOG_ROOT/$FILENAME

    echo $FOLDER_FILENAME

echo "<?xml version=\"1.1\" encoding=\"UTF-8\" standalone=\"no\"?>
<databaseChangeLog
        xmlns=\"http://www.liquibase.org/xml/ns/dbchangelog\"
        xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"
        xsi:schemaLocation=
     \"http://www.liquibase.org/xml/ns/dbchangelog
     http://www.liquibase.org/xml/ns/dbchangelog/dbchangelog-3.5.xsd\">

    <changeSet author=\"$USER (generated)\" id=\"$DATE-1\">

    </changeSet>

 </databaseChangeLog>" > $FOLDER_FILENAME
fi
exit 0
