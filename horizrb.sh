#!/bin/bash
# find assets/blockstates/ -name "*_corbi.json" -exec bash horizrb.sh {} \;
for arg; do
    for file in $(find assets/blockstates -name "$arg.json"); do
        fn="${file:19:-5}"
        cat >"$file" <<EOF
{
    "variants": {
        "facing=north": {
            "model": "testing:block/$fn"
        },
        "facing=east": {
            "model": "testing:block/$fn",
            "y": 90
        },
        "facing=south": {
            "model": "testing:block/$fn",
            "y": 180
        },
        "facing=west": {
            "model": "testing:block/$fn",
            "y": 270
        }
    }
}
EOF
        echo "$arg > $file"
    done
done
