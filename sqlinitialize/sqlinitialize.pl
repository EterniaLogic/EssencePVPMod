#!/usr/bin/perl -w

# This file is part of EssencePvP.

# EssencePvP is free software: you can redistribute it and/or modify
# it under the terms of the GNU General Public License as published by
# the Free Software Foundation, either version 3 of the License, or
# (at your option) any later version.

# EssencePvP is distributed in the hope that it will be useful,
# but WITHOUT ANY WARRANTY; without even the implied warranty of
# MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
# GNU General Public License for more details.

# You should have received a copy of the GNU General Public License
# along with EssencePvP.  If not, see <http://www.gnu.org/licenses/>.

# AK

use strict;

use Cwd;
use DBI; # libdbi-perl && libdbd-mysql-perl
use File::Path;

my %hDatabase = (
	'essencePvP' => {
				'username' => 'root',
				'password' => 'nemo20',
				'host'	   => 'localhost',
				'port'	   => '3306'
       	                }
);

my %hTables = (
	'Profession'	=> { # Contains all profession descriptions and names
					'ID'			=> { 'type' => 'int' },
					'Name'			=> { 'type' => 'varchar(250)' },
					'Description'	=> { 'type' => 'varchar(250)' },
					'Icon'			=> { 'type' => 'varchar(250)' }
				   },
	'Ability'		=> { # Contains all abilitiy descriptions and names
					'ID'			=> { 'type' => 'int' },
					'Profession'	=> { 'type' => 'int' }, # The ID to the profession that owns this ability row
					'Name'			=> { 'type' => 'varchar(250)' },
					'Description'	=> { 'type' => 'varchar(250)' },
					'Icon'			=> { 'type' => 'varchar(250)' }
				   },
	'Ability_Property'	=> { # Contains data about all abilities
					'ID'			=> { 'type' => 'int' },
					'Ability'		=> { 'type' => 'int' }, # The ID to the ability which this property belongs to
					'Name'			=> { 'type' => 'varchar(250)' },
					'Description'	=> { 'type' => 'varchar(250)' },
					'Type'			=> { 'type' => 'int' },
					'Value'			=> { 'type' => 'int' }
				   },
	'Characters'		=> {
					'ID'			=> { 'type' => 'int' },
					'PlayerName'	=> { 'type' => 'varchar(250)' },
					'PlayerUID'		=> { 'type' => 'varchar(250)' },
					'ClassAbilities'=> { 'type' => 'int' },
					'FactionID'		=> { 'type' => 'int' }
				   },
	'Factions'		=> {
					'ID'			=> { 'type' => 'int' },
					'Leader'		=> { 'type' => 'varchar(250)' },
					'Grade'			=> { 'type' => 'int' },
					'RegionsID'		=> { 'type' => 'int' },
				   },
);

sub Main(){
	my $hDatabase = &connectToDatabase('essencePvP');
	&generateTableQueries($hDatabase);
}
&Main();

#####################
##      mySQL      ##
#####################

sub connectToDatabase(){
	if(@_ == 1){
		my $sDatabase = $_[0];
		my $sUsername = $hDatabase{$sDatabase}{'username'};
		my $sPassword = $hDatabase{$sDatabase}{'password'};
		my $sHostname = $hDatabase{$sDatabase}{'host'};
		my $sPort     = $hDatabase{$sDatabase}{'port'};

		my $sConnection = "DBI:mysql:database=$sDatabase;host=$sHostname;port=$sPort";
		my $hDatabase = DBI->connect($sConnection, $sUsername, $sPassword);

		return $hDatabase;
	} else { return 0; }
}

sub exeSQLQuery(){
	if(@_ == 2){
		my ($hDatabase, $sQuery) = @_;
		my $hQuery = $hDatabase->prepare($sQuery);
		$hQuery->execute();

		return 1;
	} else { return 0; }
}

#####################
##      Parsing    ##
#####################

sub generateTableQueries(){
	if(@_ == 1){
		my $hDatabase = $_[0];

		foreach my $sTable (keys %hTables){
			my $sQuery = "CREATE TABLE $sTable (";
			my $bFirst = 1;
			foreach my $sField (keys %{$hTables{$sTable}}){
				unless($bFirst){ $sQuery = $sQuery.','; }
				my $sType = $hTables{$sTable}{$sField}{'type'};

				$sQuery = $sQuery.$sField.' '.$sType;
				$bFirst = 0;
			}
			$sQuery = $sQuery.');';
			&exeSQLQuery($hDatabase, $sQuery);
		}
	} else { return 0; }
}
