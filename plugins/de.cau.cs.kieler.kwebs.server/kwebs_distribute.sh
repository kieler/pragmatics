# Distribute the nightly KWebS build on the server
# responsible for publishing the layout service
#
# Author swe
# Last edit 21.07.2011

# Create and/or clear necessary folders if absent or clear its content
sudo -u kieler ssh layout@layout 'mkdir -p /home/layout/kwebs-nightly; mkdir -p /home/layout/kwebs-backup; rm -rf /home/layout/kwebs-nightly/kwebs_*-linux.gtk.x86_64.zip; rm -rf /home/layout/kwebs-backup/*'

# Copy the latest linux x64 build to the server
sudo -u kieler scp `ls /home/kieler/HudsonWorkingDir/jobs/Product_KWEBS_RCA/workspace/N.kwebs/kwebs_*-linux.gtk.x86_64.zip` layout@layout:kwebs-nightly

# Stop the layout service if it is running
sudo -u kieler ssh layout@layout '[ -d /home/layout/kwebs ] && cd kwebs; ./kwebs_stop.sh || echo "KWebS does not exist yet on the server, no need to stop it"'

# Backup user configurable files
sudo -u kieler ssh layout@layout '[ -f /home/layout/kwebs/kwebs.user ] && mv -f /home/layout/kwebs/kwebs.user /home/layout/kwebs-backup || echo "no user config to backup"'

# Delete the prior distribution
sudo -u kieler ssh layout@layout 'rm -rf /home/layout/kwebs'

# Unzip the newly uploaded distribution
sudo -u kieler ssh layout@layout 'unzip kwebs-nightly/kwebs_*-linux.gtk.x86_64.zip'

# Restore user configurable files
sudo -u kieler ssh layout@layout '[ -f /home/layout/kwebs-backup/kwebs.user ] && mv -f /home/layout/kwebs-backup/kwebs.user /home/layout/kwebs || echo "no user config to restore"'

# Restore scripts
#sudo -u kieler ssh layout@layout '[ -d /home/layout/kwebs-scripts ] && cp -f /home/layout/kwebs-scripts/kwebs_*.sh /home/layout/kwebs || echo "no scripts to restore"'

# Set scripts to executable (just to be sure) and start the layout service
sudo -u kieler ssh layout@layout 'cd kwebs; chmod u+x kwebs_*.sh; ./kwebs_start.sh'

