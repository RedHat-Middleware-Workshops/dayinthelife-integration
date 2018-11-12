
find . -name '*.adoc' -exec perl -0pi -e 's/Lab(.*)\n\n== (.*)\n\n=== /Lab \1 \2\n\n/g' {} +

find . -name '*.adoc' -exec perl -0pi -e 's/== Overview/*Overview*/g' {} +

find . -name '*.adoc' -exec perl -0pi -e 's/=== Why Red Hat.*/*Why Red Hat*/g' {} +

find . -name '*.adoc' -exec perl -0pi -e 's/=== Skipping The Lab/*Skipping The Lab*/g' {} +

find . -name '*.adoc' -exec perl -0pi -e 's/=== Environment/*Environment*/g' {} +

find . -name '*.adoc' -exec perl -0pi -e 's/== Steps Beyond/*Steps Beyond*/g' {} +


find . -name '*.adoc' -exec perl -0pi -e 's/== Summary/*Summary*/g' {} +

find . -name '*.adoc' -exec perl -0pi -e 's/== Notes and Further Reading/*Notes and Further Reading*/g' {} +

find . -name '*.adoc' -exec perl -0pi -e 's/== Lab Instructions/== Lab Instructions\n\nPerform the steps./g' {} +

