// EssencePvP is free software: you can redistribute it and/or modify
// it under the terms of the GNU General Public License as published by
// the Free Software Foundation, either version 3 of the License, or
// (at your option) any later version.

// EssencePvP is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
// GNU General Public License for more details.

// You should have received a copy of the GNU General Public License
// along with EssencePvP.  If not, see <http://www.gnu.org/licenses/>.

package com.EssencePVP;


class PrimaryThread extends Thread {
	//private List<Player> isFlying = Collections.synchronizedList(new ArrayList<Player>());
	//private ConcurrentHashMap<Player, Integer> playerTimes = new ConcurrentHashMap<Player, Integer>();
	
	@SuppressWarnings("unused")
	@Override
	public void run(){
		if (true) return;
		load();
		while (true){
			try {
				Thread.sleep(1000); // 1 Second
			} catch (InterruptedException e) {break;}
			
			// process background information
		}
	}
	private void load(){ reload(); }
	public void reload() {
		
	}
}
