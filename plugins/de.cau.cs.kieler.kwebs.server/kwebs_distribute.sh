# Distribute the nightly KWebS build on the server
# responsible for publishing the layout service
#
# Author swe
# Last edit 10.08.2011

# Create and/or clear necessary folders if absent or clear its content
sudo -u kieler ssh layout@layout 'mkdir -p /home/layout/kwebs-nightly; mkdir -p /home/layout/kwebs-backup; rm -rf /home/layout/kwebs-nightly/kwebs_*-linux.gtk.x86_64.zip; rm -rf /home/layout/kwebs-backup/*'

# Copy the latest linux x64 build to the server
sudo -u kieler scp `ls $WORKSPACE/N.kwebs/kwebs_*-linux.gtk.x86_64.zip` layout@layout:kwebs-nightly

# Set scripts to executable
sudo -u kieler ssh layout@layout 'cd kwebs; chmod -R u+x *.sh'

# Stop the layout service if it is running
sudo -u kieler ssh layout@layout '[ -f /home/layout/kwebs/kwebs_stop.sh ] && cd kwebs; ./kwebs_stop.sh'

# Backup user configurable files
sudo -u kieler ssh layout@layout '[ -f /home/layout/kwebs/kwebs_backup.sh ] && cd kwebs; ./kwebs_backup.sh'

# Delete the prior distribution
sudo -u kieler ssh layout@layout '[ -d /home/layout/kwebs ] && rm -rf /home/layout/kwebs'

# Unzip the newly uploaded distribution
sudo -u kieler ssh layout@layout 'unzip kwebs-nightly/kwebs_*-linux.gtk.x86_64.zip'

# Extract kwebs management scripts from server plugin archive
sudo -u kieler ssh layout@layout 'chmod u+x bootstrap.sh; ./bootstrap.sh'

# Set scripts to executable, executable status may have changed after fresh deployment
sudo -u kieler ssh layout@layout 'cd kwebs; chmod -R u+x *.sh'

# Restore user configurable files
sudo -u kieler ssh layout@layout 'cd kwebs; ./kwebs_restore.sh'

# Set scripts to executable (just to be sure) and start the layout service
sudo -u kieler ssh layout@layout 'cd kwebs; ./kwebs_start.sh'
