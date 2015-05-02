all: setperm addTask

setperm: installer.sh scheduler.sh
	chmod 755 installer.sh scheduler.sh

addTask: installer.sh scheduler.sh
	bash installer.sh

clean: cronfile.txt
	rm cronfile.txt

cronclear:
	crontab -r -i
