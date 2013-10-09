#!/bin/bash

echo ""

help="\nusage: install_kieler_features [kgraph|ptolemy|klighd.examples|featureId]"

if [[ -z "$1" ]];
then 
	echo "Error: Provide a comma separated list with kieler features you would like to install."
	echo -e "${help}"
	exit 1
fi

# use our default repositories
rep_platform=http://rtsys.informatik.uni-kiel.de/~kieler/repository/kepler43/
rep_pragmatics=http://rtsys.informatik.uni-kiel.de/~kieler/updatesite/nightly/pragmatics/
rep_ptolemy=http://rtsys.informatik.uni-kiel.de/~kieler/updatesite/ptolemy/

repositories=${rep_platform},${rep_pragmatics},${rep_ptolemy}

# split the feature input
IFS=','
features=($1)

for ((i=0; i < ${#features[@]}; i++))
do
  # kgraph (e.g., *.kgt format)
  if [ ${features[$i]} == "kgraph" ]; 
  then 
	features[$i]="de.cau.cs.kieler.kgraph.feature.feature.group"; 
  fi
  
  # ptolemy visualization (*.moml)
  if [ ${features[$i]} == "ptolemy" ]; 
  then 
	features[$i]="de.cau.cs.kieler.ptview.feature.feature.group"; 
  fi
  
  # several klighd examples (e.g., ecore class diagrams)
  if [ ${features[$i]} == "klighd.examples" ]; 
  then 
	features[$i]="de.cau.cs.kieler.klighd.examples.feature.feature.group"; 
  fi

  #echo "Found ${features[$i]} ..."
done

# join the features again
IFS=","
iuFeatures="${features[*]}"

#echo "${iuFeatures}"

./install_features.sh "${iuFeatures}" "${repositories}"