<article class="markdown-body entry-content container-lg" itemprop="text"><h1 dir="auto"><a id="user-content-carol-scout" class="anchor" aria-hidden="true" href="#carol-scout"><svg class="octicon octicon-link" viewBox="0 0 16 16" version="1.1" width="16" height="16" aria-hidden="true"><path fill-rule="evenodd" d="M7.775 3.275a.75.75 0 001.06 1.06l1.25-1.25a2 2 0 112.83 2.83l-2.5 2.5a2 2 0 01-2.83 0 .75.75 0 00-1.06 1.06 3.5 3.5 0 004.95 0l2.5-2.5a3.5 3.5 0 00-4.95-4.95l-1.25 1.25zm-4.69 9.64a2 2 0 010-2.83l2.5-2.5a2 2 0 012.83 0 .75.75 0 001.06-1.06 3.5 3.5 0 00-4.95 0l-2.5 2.5a3.5 3.5 0 004.95 4.95l1.25-1.25a.75.75 0 00-1.06-1.06l-1.25 1.25a2 2 0 01-2.83 0z"></path></svg></a>Carol Scout</h1>
<p dir="auto">Carol really liked the "Penguin Carol" simulator that we implemented in homework PINGU CAROL and has already proven to be useful on a few expeditions.</p>
<p dir="auto">Now Carol came up with more ideas and has a new assignment for us: She would like a program that not only allows the simulation of different situations, but that can even independently find (optimal) paths through the Penguin Carol world to get from to come from one place to the next.</p>
<p dir="auto"><strong>The rules for how Carol can move around the field and what instructions are allowed in certain situations are the same as those described in PINGU CAROL task.</strong></p>
<p dir="auto">However, the two-dimensional array is passed as a parameter here. In this task you can also assume that all parameters passed are valid. In MiniJava there are still methods for displaying the playing field. You are welcome to use this as you like, because in this task we no longer test for the output on the console.</p>
<h6 id="user-content-shortsummary" dir="auto"><a id="user-content-short-summary" class="anchor" aria-hidden="true" href="#short-summary"><svg class="octicon octicon-link" viewBox="0 0 16 16" version="1.1" width="16" height="16" aria-hidden="true"><path fill-rule="evenodd" d="M7.775 3.275a.75.75 0 001.06 1.06l1.25-1.25a2 2 0 112.83 2.83l-2.5 2.5a2 2 0 01-2.83 0 .75.75 0 00-1.06 1.06 3.5 3.5 0 004.95 0l2.5-2.5a3.5 3.5 0 00-4.95-4.95l-1.25 1.25zm-4.69 9.64a2 2 0 010-2.83l2.5-2.5a2 2 0 012.83 0 .75.75 0 001.06-1.06 3.5 3.5 0 00-4.95 0l-2.5 2.5a3.5 3.5 0 004.95 4.95l1.25-1.25a.75.75 0 00-1.06-1.06l-1.25 1.25a2 2 0 01-2.83 0z"></path></svg></a>Short Summary</h6>
<p dir="auto">As in PINGU CAROL, <code>playground.length</code> is the width of the playing field and <code>playground[0].length</code> is the height. The coordinate system still has its origin at the bottom left and <code>playground[x][y]</code> is the height of the field at position (x, y). The height is a value from <code>-1</code> to <code>9</code>. <code>-1</code> is water that is a block of ice deep. The height is the number of ice blocks above the water, and the number of ice blocks on a field is equal to the height plus one. Carol can move within this playing field and carry around <code>0</code> to <code>10</code> blocks of ice. Carol's viewing direction can be <code>0</code> (pos. X / right), <code>1</code> (pos. Y / up), <code>2</code> (neg. X / left) and <code>3</code> (neg. Y / down). The following instructions can be used to navigate the field:</p>
<ul dir="auto">
<li><code>'r'</code> Carol turns right from her own point of view.</li>
<li><code>'l'</code> Carol turns left from her own point of view.</li>
<li><code>'s'</code> Carol takes a step in the current direction of gaze. For this, the absolute height difference must be less than or equal to one.</li>
<li><code>'p'</code>  Carol places a block of ice on the field in the line of sight. To do this, she must carry at least one block of ice and the field in front of her must not have reached the maximum height (<code>9</code>). If Carol is in the water, she cannot place blocks of ice either.</li>
<li><code>'n'</code> Carol takes a block of ice from the field in the direction of view. To do this, she must be able to pick up at least one block of ice and the space in front of her must not be water (<code>-1</code>). If Carol is in the water, she cannot take any blocks of ice either.</li>
</ul>
<p dir="auto">Of course, Carol cannot move outside the field, take or place blocks there.</p>
<h4 id="user-content-analyzecarolsinstructions" dir="auto"><a id="user-content-analyze-carols-instructions" class="anchor" aria-hidden="true" href="#analyze-carols-instructions"><svg class="octicon octicon-link" viewBox="0 0 16 16" version="1.1" width="16" height="16" aria-hidden="true"><path fill-rule="evenodd" d="M7.775 3.275a.75.75 0 001.06 1.06l1.25-1.25a2 2 0 112.83 2.83l-2.5 2.5a2 2 0 01-2.83 0 .75.75 0 00-1.06 1.06 3.5 3.5 0 004.95 0l2.5-2.5a3.5 3.5 0 00-4.95-4.95l-1.25 1.25zm-4.69 9.64a2 2 0 010-2.83l2.5-2.5a2 2 0 012.83 0 .75.75 0 001.06-1.06 3.5 3.5 0 00-4.95 0l-2.5 2.5a3.5 3.5 0 004.95 4.95l1.25-1.25a.75.75 0 00-1.06-1.06l-1.25 1.25a2 2 0 01-2.83 0z"></path></svg></a>Analyze Carol's instructions</h4>
<p dir="auto">First, let's write a few helper methods to better understand Carol's current position on the field. We want to analyze a sequence of Penguin Carol instructions that is stored in an array. It is particularly interesting whether the last instructions stored in an array make sense. So that we can later use the methods efficiently for the pathfinder program, we enter in the parameter filled with two methods how many instructions the array is currently filled with (the point here is to be able to use an array over and over again). None of the following three methods may change the content of the array.</p>
<h5 id="user-content-checkifthelasttwistsareuseless" dir="auto"><a id="user-content-check-if-the-last-twists-are-useless" class="anchor" aria-hidden="true" href="#check-if-the-last-twists-are-useless"><svg class="octicon octicon-link" viewBox="0 0 16 16" version="1.1" width="16" height="16" aria-hidden="true"><path fill-rule="evenodd" d="M7.775 3.275a.75.75 0 001.06 1.06l1.25-1.25a2 2 0 112.83 2.83l-2.5 2.5a2 2 0 01-2.83 0 .75.75 0 00-1.06 1.06 3.5 3.5 0 004.95 0l2.5-2.5a3.5 3.5 0 00-4.95-4.95l-1.25 1.25zm-4.69 9.64a2 2 0 010-2.83l2.5-2.5a2 2 0 012.83 0 .75.75 0 001.06-1.06 3.5 3.5 0 00-4.95 0l-2.5 2.5a3.5 3.5 0 004.95 4.95l1.25-1.25a.75.75 0 00-1.06-1.06l-1.25 1.25a2 2 0 01-2.83 0z"></path></svg></a>Check if the last twists are useless</h5>
<p dir="auto">Method head:</p>
<div class="snippet-clipboard-content notranslate position-relative overflow-auto"><pre class="notranslate"><code><span>static</span> <span>boolean</span> <span>lastTurnsAreUseless</span><span>(<span>char</span>[] instr, <span>int</span> filled)</span>
</code></pre><div class="zeroclipboard-container position-absolute right-0 top-0">
    <clipboard-copy aria-label="Copy" class="ClipboardButton btn js-clipboard-copy m-2 p-0 tooltipped-no-delay" data-copy-feedback="Copied!" data-tooltip-direction="w" value="static boolean lastTurnsAreUseless(char[] instr, int filled)" tabindex="0" role="button">
      <svg aria-hidden="true" height="16" viewBox="0 0 16 16" version="1.1" width="16" data-view-component="true" class="octicon octicon-copy js-clipboard-copy-icon m-2">
    <path fill-rule="evenodd" d="M0 6.75C0 5.784.784 5 1.75 5h1.5a.75.75 0 010 1.5h-1.5a.25.25 0 00-.25.25v7.5c0 .138.112.25.25.25h7.5a.25.25 0 00.25-.25v-1.5a.75.75 0 011.5 0v1.5A1.75 1.75 0 019.25 16h-7.5A1.75 1.75 0 010 14.25v-7.5z"></path><path fill-rule="evenodd" d="M5 1.75C5 .784 5.784 0 6.75 0h7.5C15.216 0 16 .784 16 1.75v7.5A1.75 1.75 0 0114.25 11h-7.5A1.75 1.75 0 015 9.25v-7.5zm1.75-.25a.25.25 0 00-.25.25v7.5c0 .138.112.25.25.25h7.5a.25.25 0 00.25-.25v-7.5a.25.25 0 00-.25-.25h-7.5z"></path>
</svg>
      <svg aria-hidden="true" height="16" viewBox="0 0 16 16" version="1.1" width="16" data-view-component="true" class="octicon octicon-check js-clipboard-check-icon color-fg-success d-none m-2">
    <path fill-rule="evenodd" d="M13.78 4.22a.75.75 0 010 1.06l-7.25 7.25a.75.75 0 01-1.06 0L2.22 9.28a.75.75 0 011.06-1.06L6 10.94l6.72-6.72a.75.75 0 011.06 0z"></path>
</svg>
    </clipboard-copy>
  </div></div>
<p dir="auto">The method <em>should return</em> <code>true</code> if and only if the last instructions in the instruction array are rotations that can be achieved differently or even more easily. <br>
Specifically, this is the case if the last two instructions are as follows:</p>
<ul dir="auto">
<li><code>'r'</code> followed by  <code>'l'</code>, or</li>
<li><code>'l'</code> followed by  <code>'r'</code>, or</li>
<li><code>'r'</code> followed by  <code>'r'</code>, because you can turn around with two  <code>'l'</code> and we don't have to try both.
Or if the last three instructions are as follows:</li>
<li>three times  <code>'l'</code>, because here an  <code>'r'</code> achieves the same thing and is easier.</li>
</ul>
<h5 id="user-content-checkthatcarolhasalreadybeentothesameplaceandhasnotchangedanyblocksoficesincethen" dir="auto"><a id="user-content-check-that-carol-has-already-been-to-the-same-place-and-has-not-changed-any-blocks-of-ice-since-then" class="anchor" aria-hidden="true" href="#check-that-carol-has-already-been-to-the-same-place-and-has-not-changed-any-blocks-of-ice-since-then"><svg class="octicon octicon-link" viewBox="0 0 16 16" version="1.1" width="16" height="16" aria-hidden="true"><path fill-rule="evenodd" d="M7.775 3.275a.75.75 0 001.06 1.06l1.25-1.25a2 2 0 112.83 2.83l-2.5 2.5a2 2 0 01-2.83 0 .75.75 0 00-1.06 1.06 3.5 3.5 0 004.95 0l2.5-2.5a3.5 3.5 0 00-4.95-4.95l-1.25 1.25zm-4.69 9.64a2 2 0 010-2.83l2.5-2.5a2 2 0 012.83 0 .75.75 0 001.06-1.06 3.5 3.5 0 00-4.95 0l-2.5 2.5a3.5 3.5 0 004.95 4.95l1.25-1.25a.75.75 0 00-1.06-1.06l-1.25 1.25a2 2 0 01-2.83 0z"></path></svg></a>Check that Carol has already been to the same place and has not changed any blocks of ice since then</h5>
<p dir="auto">Method head:</p>
<div class="snippet-clipboard-content notranslate position-relative overflow-auto"><pre class="notranslate"><code><span>static</span> <span>boolean</span> <span>wasThereBefore</span><span>(<span>char</span>[] instr, <span>int</span> filled)</span>
</code></pre><div class="zeroclipboard-container position-absolute right-0 top-0">
    <clipboard-copy aria-label="Copy" class="ClipboardButton btn js-clipboard-copy m-2 p-0 tooltipped-no-delay" data-copy-feedback="Copied!" data-tooltip-direction="w" value="static boolean wasThereBefore(char[] instr, int filled)" tabindex="0" role="button">
      <svg aria-hidden="true" height="16" viewBox="0 0 16 16" version="1.1" width="16" data-view-component="true" class="octicon octicon-copy js-clipboard-copy-icon m-2">
    <path fill-rule="evenodd" d="M0 6.75C0 5.784.784 5 1.75 5h1.5a.75.75 0 010 1.5h-1.5a.25.25 0 00-.25.25v7.5c0 .138.112.25.25.25h7.5a.25.25 0 00.25-.25v-1.5a.75.75 0 011.5 0v1.5A1.75 1.75 0 019.25 16h-7.5A1.75 1.75 0 010 14.25v-7.5z"></path><path fill-rule="evenodd" d="M5 1.75C5 .784 5.784 0 6.75 0h7.5C15.216 0 16 .784 16 1.75v7.5A1.75 1.75 0 0114.25 11h-7.5A1.75 1.75 0 015 9.25v-7.5zm1.75-.25a.25.25 0 00-.25.25v7.5c0 .138.112.25.25.25h7.5a.25.25 0 00.25-.25v-7.5a.25.25 0 00-.25-.25h-7.5z"></path>
</svg>
      <svg aria-hidden="true" height="16" viewBox="0 0 16 16" version="1.1" width="16" data-view-component="true" class="octicon octicon-check js-clipboard-check-icon color-fg-success d-none m-2">
    <path fill-rule="evenodd" d="M13.78 4.22a.75.75 0 010 1.06l-7.25 7.25a.75.75 0 01-1.06 0L2.22 9.28a.75.75 0 011.06-1.06L6 10.94l6.72-6.72a.75.75 0 011.06 0z"></path>
</svg>
    </clipboard-copy>
  </div></div>
<p dir="auto">The method is to check the instructions starting from the last instruction to see whether Carol has already been in the same place before the last instruction <em>without having placed or taken a block of ice in between</em>. In this case the method should return <code>true</code>.</p>
<h5 id="user-content-checktheminimumnumberofstepsandturnsrequired" dir="auto"><a id="user-content-check-the-minimum-number-of-steps-and-turns-required" class="anchor" aria-hidden="true" href="#check-the-minimum-number-of-steps-and-turns-required"><svg class="octicon octicon-link" viewBox="0 0 16 16" version="1.1" width="16" height="16" aria-hidden="true"><path fill-rule="evenodd" d="M7.775 3.275a.75.75 0 001.06 1.06l1.25-1.25a2 2 0 112.83 2.83l-2.5 2.5a2 2 0 01-2.83 0 .75.75 0 00-1.06 1.06 3.5 3.5 0 004.95 0l2.5-2.5a3.5 3.5 0 00-4.95-4.95l-1.25 1.25zm-4.69 9.64a2 2 0 010-2.83l2.5-2.5a2 2 0 012.83 0 .75.75 0 001.06-1.06 3.5 3.5 0 00-4.95 0l-2.5 2.5a3.5 3.5 0 004.95 4.95l1.25-1.25a.75.75 0 00-1.06-1.06l-1.25 1.25a2 2 0 01-2.83 0z"></path></svg></a>Check the minimum number of steps and turns required</h5>
<p dir="auto">Method head:</p>
<div class="snippet-clipboard-content notranslate position-relative overflow-auto"><pre class="notranslate"><code><span>static</span> <span>int</span> <span>getMinimalStepsAndTurns</span><span>(<span>int</span> x, <span>int</span> y, <span>int</span> direction, <span>int</span> findX, <span>int</span> findY)</span>
</code></pre><div class="zeroclipboard-container position-absolute right-0 top-0">
    <clipboard-copy aria-label="Copy" class="ClipboardButton btn js-clipboard-copy m-2 p-0 tooltipped-no-delay" data-copy-feedback="Copied!" data-tooltip-direction="w" value="static int getMinimalStepsAndTurns(int x, int y, int direction, int findX, int findY)" tabindex="0" role="button">
      <svg aria-hidden="true" height="16" viewBox="0 0 16 16" version="1.1" width="16" data-view-component="true" class="octicon octicon-copy js-clipboard-copy-icon m-2">
    <path fill-rule="evenodd" d="M0 6.75C0 5.784.784 5 1.75 5h1.5a.75.75 0 010 1.5h-1.5a.25.25 0 00-.25.25v7.5c0 .138.112.25.25.25h7.5a.25.25 0 00.25-.25v-1.5a.75.75 0 011.5 0v1.5A1.75 1.75 0 019.25 16h-7.5A1.75 1.75 0 010 14.25v-7.5z"></path><path fill-rule="evenodd" d="M5 1.75C5 .784 5.784 0 6.75 0h7.5C15.216 0 16 .784 16 1.75v7.5A1.75 1.75 0 0114.25 11h-7.5A1.75 1.75 0 015 9.25v-7.5zm1.75-.25a.25.25 0 00-.25.25v7.5c0 .138.112.25.25.25h7.5a.25.25 0 00.25-.25v-7.5a.25.25 0 00-.25-.25h-7.5z"></path>
</svg>
      <svg aria-hidden="true" height="16" viewBox="0 0 16 16" version="1.1" width="16" data-view-component="true" class="octicon octicon-check js-clipboard-check-icon color-fg-success d-none m-2">
    <path fill-rule="evenodd" d="M13.78 4.22a.75.75 0 010 1.06l-7.25 7.25a.75.75 0 01-1.06 0L2.22 9.28a.75.75 0 011.06-1.06L6 10.94l6.72-6.72a.75.75 0 011.06 0z"></path>
</svg>
    </clipboard-copy>
  </div></div>
<p dir="auto">This method should return the minimum number of steps and rotations that are required to get from the current situation (<code>x</code>, <code>y</code>, <code>direction</code>) to the destination (<code>findX</code>, <code>findY</code>). This value should not take into account the heights, blocks of ice or the like (therefore it is only a minimum in relation to the overall situation). <em>However, this minimum must be optimal for the given parameters.</em> (I.e. you may not always return <code>0</code>as a minimum, for example.)</p>
<h4 id="user-content-findinginstructions" dir="auto"><a id="user-content-finding-instructions" class="anchor" aria-hidden="true" href="#finding-instructions"><svg class="octicon octicon-link" viewBox="0 0 16 16" version="1.1" width="16" height="16" aria-hidden="true"><path fill-rule="evenodd" d="M7.775 3.275a.75.75 0 001.06 1.06l1.25-1.25a2 2 0 112.83 2.83l-2.5 2.5a2 2 0 01-2.83 0 .75.75 0 00-1.06 1.06 3.5 3.5 0 004.95 0l2.5-2.5a3.5 3.5 0 00-4.95-4.95l-1.25 1.25zm-4.69 9.64a2 2 0 010-2.83l2.5-2.5a2 2 0 012.83 0 .75.75 0 001.06-1.06 3.5 3.5 0 00-4.95 0l-2.5 2.5a3.5 3.5 0 004.95 4.95l1.25-1.25a.75.75 0 00-1.06-1.06l-1.25 1.25a2 2 0 01-2.83 0z"></path></svg></a>Finding instructions</h4>
<p dir="auto">Now we come to the actual part: we want to find paths for Carol to get from an initial situation <code>x</code>, <code>y</code>, <code>direction</code> to a target position <code>findX</code>, <code>findY</code> on a given playing field <code>playground</code>. To do this, implement a method with the following method head:</p>
<div class="snippet-clipboard-content notranslate position-relative overflow-auto"><pre class="notranslate"><code><span>public</span> <span>static</span> <span>boolean</span> <span>findInstructions</span><span>(<span>int</span>[][] playground, <span>int</span> x, <span>int</span> y, <span>int</span> direction, <span>int</span> blocks, <span>int</span> findX, <span>int</span> findY, <span>char</span>[] instructions)</span>
</code></pre><div class="zeroclipboard-container position-absolute right-0 top-0">
    <clipboard-copy aria-label="Copy" class="ClipboardButton btn js-clipboard-copy m-2 p-0 tooltipped-no-delay" data-copy-feedback="Copied!" data-tooltip-direction="w" value="public static boolean findInstructions(int[][] playground, int x, int y, int direction, int blocks, int findX, int findY, char[] instructions)" tabindex="0" role="button">
      <svg aria-hidden="true" height="16" viewBox="0 0 16 16" version="1.1" width="16" data-view-component="true" class="octicon octicon-copy js-clipboard-copy-icon m-2">
    <path fill-rule="evenodd" d="M0 6.75C0 5.784.784 5 1.75 5h1.5a.75.75 0 010 1.5h-1.5a.25.25 0 00-.25.25v7.5c0 .138.112.25.25.25h7.5a.25.25 0 00.25-.25v-1.5a.75.75 0 011.5 0v1.5A1.75 1.75 0 019.25 16h-7.5A1.75 1.75 0 010 14.25v-7.5z"></path><path fill-rule="evenodd" d="M5 1.75C5 .784 5.784 0 6.75 0h7.5C15.216 0 16 .784 16 1.75v7.5A1.75 1.75 0 0114.25 11h-7.5A1.75 1.75 0 015 9.25v-7.5zm1.75-.25a.25.25 0 00-.25.25v7.5c0 .138.112.25.25.25h7.5a.25.25 0 00.25-.25v-7.5a.25.25 0 00-.25-.25h-7.5z"></path>
</svg>
      <svg aria-hidden="true" height="16" viewBox="0 0 16 16" version="1.1" width="16" data-view-component="true" class="octicon octicon-check js-clipboard-check-icon color-fg-success d-none m-2">
    <path fill-rule="evenodd" d="M13.78 4.22a.75.75 0 010 1.06l-7.25 7.25a.75.75 0 01-1.06 0L2.22 9.28a.75.75 0 011.06-1.06L6 10.94l6.72-6.72a.75.75 0 011.06 0z"></path>
</svg>
    </clipboard-copy>
  </div></div>
<p dir="auto">In addition to the parameters already mentioned, we also transfer a <code>char</code>-Array <code>instructions</code> to the method. The instruction sequence that leads Carol to her goal is to be stored in this array. Exactly this transferred array should be used, which (as usual for arrays) has a <strong>fixed length.</strong> The method must therefore look for solutions that require a maximum of <code>instructions.length</code> instructions (this also limits the computational effort).</p>
<p dir="auto">Since it is possible that such a solution does not exist, <code>false</code> should be returned if and only if the search was unsuccessful. However, if the search was successful, <code>true</code> is returned and <code>instructions</code> contain a sequence of instructions with which Carol gets to the goal. If this sequence is shorter than the array, all remaining fields should be filled with <code>'e'</code>, the end instruction in the Penguin Carol simulation. <strong>Solve this problem using recursion.</strong></p>
<p dir="auto"><em>If at least one solution exists that fulfills the requirements passed in the arguments, your implementation of the <code>findInstructions</code> method must find such a solution.</em></p>
<p dir="auto">Useful notes on implementation:</p>
<ul dir="auto">
<li>Write a <em>recursive</em> auxiliary method that accepts the same parameters as <code>findInstructions</code> and also an <code>int filled</code> with which you can pass on how many <code>instructions</code> are currently in instructions.</li>
<li>For each recursive call, try out which next instruction leads to the goal. Here you can try out all possible and permitted instructions. Make sure that changes to the tried-out instruction are passed on to the recursive call and, if necessary, reversed later.</li>
<li>Make sure you use the previously implemented methods to analyze the next instructions at the appropriate places in order to be able to exclude options and to determine whether a solution is still possible with the currently selected path. Otherwise your algorithm will be very slow. You should also incorporate additional checks in order to avoid <code>'p'</code> directly after <code>'n'</code> and vice versa.</li>
<li>Some instructions are more "productive" than others, so the order in which you try instructions can also play a role in the speed of execution. Here you can try out what works well, among other things.</li>
<li><em>Optional: You are welcome to implement further optimizations if you want. But then pay close attention to the runtime of these optimizations (and the correctness). Many are not worth the computational effort.</em></li>
</ul>
<h4 id="user-content-findanoptimalsequenceofinstructions" dir="auto"><a id="user-content-find-an-optimal-sequence-of-instructions" class="anchor" aria-hidden="true" href="#find-an-optimal-sequence-of-instructions"><svg class="octicon octicon-link" viewBox="0 0 16 16" version="1.1" width="16" height="16" aria-hidden="true"><path fill-rule="evenodd" d="M7.775 3.275a.75.75 0 001.06 1.06l1.25-1.25a2 2 0 112.83 2.83l-2.5 2.5a2 2 0 01-2.83 0 .75.75 0 00-1.06 1.06 3.5 3.5 0 004.95 0l2.5-2.5a3.5 3.5 0 00-4.95-4.95l-1.25 1.25zm-4.69 9.64a2 2 0 010-2.83l2.5-2.5a2 2 0 012.83 0 .75.75 0 001.06-1.06 3.5 3.5 0 00-4.95 0l-2.5 2.5a3.5 3.5 0 004.95 4.95l1.25-1.25a.75.75 0 00-1.06-1.06l-1.25 1.25a2 2 0 01-2.83 0z"></path></svg></a>Find an optimal sequence of instructions</h4>
<p dir="auto">Implement the <code>findOptimalSolution</code> method, which has the following method head:</p>
<div class="snippet-clipboard-content notranslate position-relative overflow-auto"><pre class="notranslate"><code><span>public</span> <span>static</span> <span>char</span>[] findOptimalSolution(<span>int</span>[][] playground, <span>int</span> x, <span>int</span> y, <span>int</span> direction, <span>int</span> blocks, <span>int</span> findX, <span>int</span> findY, <span>int</span> searchLimit)
</code></pre><div class="zeroclipboard-container position-absolute right-0 top-0">
    <clipboard-copy aria-label="Copy" class="ClipboardButton btn js-clipboard-copy m-2 p-0 tooltipped-no-delay" data-copy-feedback="Copied!" data-tooltip-direction="w" value="public static char[] findOptimalSolution(int[][] playground, int x, int y, int direction, int blocks, int findX, int findY, int searchLimit)" tabindex="0" role="button">
      <svg aria-hidden="true" height="16" viewBox="0 0 16 16" version="1.1" width="16" data-view-component="true" class="octicon octicon-copy js-clipboard-copy-icon m-2">
    <path fill-rule="evenodd" d="M0 6.75C0 5.784.784 5 1.75 5h1.5a.75.75 0 010 1.5h-1.5a.25.25 0 00-.25.25v7.5c0 .138.112.25.25.25h7.5a.25.25 0 00.25-.25v-1.5a.75.75 0 011.5 0v1.5A1.75 1.75 0 019.25 16h-7.5A1.75 1.75 0 010 14.25v-7.5z"></path><path fill-rule="evenodd" d="M5 1.75C5 .784 5.784 0 6.75 0h7.5C15.216 0 16 .784 16 1.75v7.5A1.75 1.75 0 0114.25 11h-7.5A1.75 1.75 0 015 9.25v-7.5zm1.75-.25a.25.25 0 00-.25.25v7.5c0 .138.112.25.25.25h7.5a.25.25 0 00.25-.25v-7.5a.25.25 0 00-.25-.25h-7.5z"></path>
</svg>
      <svg aria-hidden="true" height="16" viewBox="0 0 16 16" version="1.1" width="16" data-view-component="true" class="octicon octicon-check js-clipboard-check-icon color-fg-success d-none m-2">
    <path fill-rule="evenodd" d="M13.78 4.22a.75.75 0 010 1.06l-7.25 7.25a.75.75 0 01-1.06 0L2.22 9.28a.75.75 0 011.06-1.06L6 10.94l6.72-6.72a.75.75 0 011.06 0z"></path>
</svg>
    </clipboard-copy>
  </div></div>
<p dir="auto">This method should use <code>findInstructions</code> to find the optimal instruction sequence. An instruction sequence is optimal if there is no shorter one that describes a path from the initial situation to the destination.</p>
<p dir="auto">The <code>searchLimit</code> is the maximum length up to which solutions should be searched for. This mainly serves to limit the search effort. The method should return <code>null</code> if no such solution exists, and otherwise a char-Array that contains the optimal instruction sequence. The array should then only be as large as necessary (i.e. not filled with an <code>'e'</code>).</p>
<h5 id="user-content-atestforamazeexample" dir="auto"><a id="user-content-a-test-for-a-maze-example" class="anchor" aria-hidden="true" href="#a-test-for-a-maze-example"><svg class="octicon octicon-link" viewBox="0 0 16 16" version="1.1" width="16" height="16" aria-hidden="true"><path fill-rule="evenodd" d="M7.775 3.275a.75.75 0 001.06 1.06l1.25-1.25a2 2 0 112.83 2.83l-2.5 2.5a2 2 0 01-2.83 0 .75.75 0 00-1.06 1.06 3.5 3.5 0 004.95 0l2.5-2.5a3.5 3.5 0 00-4.95-4.95l-1.25 1.25zm-4.69 9.64a2 2 0 010-2.83l2.5-2.5a2 2 0 012.83 0 .75.75 0 001.06-1.06 3.5 3.5 0 00-4.95 0l-2.5 2.5a3.5 3.5 0 004.95 4.95l1.25-1.25a.75.75 0 00-1.06-1.06l-1.25 1.25a2 2 0 01-2.83 0z"></path></svg></a>A test for a maze example</h5>
<p dir="auto">The target position is (6, 5), the starting position with start position (0,0), viewing direction below:</p>
<div class="snippet-clipboard-content notranslate position-relative overflow-auto"><pre class="notranslate"><code>┏━━━┯━━━┯━━━┯━━━┯━━━┯━━━┯━━━┯━━━┯━━━┓
┃ 2 │ ~ │ ~ │ ~ │ ~ │ 2 │ ~ │ ~ │ ~ ┃
┠───┼───┼───┼───┼───┼───┼───┼───┼───┨
┃ ~ │ ~ │ 2 │ ~ │ ~ │ 2 │ ~ │ 2 │ ~ ┃
┠───┼───┼───┼───┼───┼───┼───┼───┼───┨
┃ ~ │ ~ │ ~ │ ~ │ ~ │ ~ │ 2 │ ~ │ ~ ┃
┠───┼───┼───┼───┼───┼───┼───┼───┼───┨
┃ 2 │ 2 │ ~ │ 2 │ 2 │ ~ │ ~ │ ~ │ 2 ┃
┠───┼───┼───┼───┼───┼───┼───┼───┼───┨
┃ ~ │ ~ │ ~ │ 2 │ ~ │ 2 │ ~ │ ~ │ ~ ┃
┠───┼───┼───┼───┼───┼───┼───┼───┼───┨
┃ ~ │ 2 │ ~ │ ~ │ ~ │ 2 │ 2 │ ~ │ ~ ┃
┠───┼───┼───┼───┼───┼───┼───┼───┼───┨
┃ ▼ │ ~ │ ~ │ 2 │ ~ │ ~ │ ~ │ ~ │ 2 ┃ Standing on height -1, carrying 0 ice blocks.
┗━━━┷━━━┷━━━┷━━━┷━━━┷━━━┷━━━┷━━━┷━━━┛</code></pre><div class="zeroclipboard-container position-absolute right-0 top-0">
    <clipboard-copy aria-label="Copy" class="ClipboardButton btn js-clipboard-copy m-2 p-0 tooltipped-no-delay" data-copy-feedback="Copied!" data-tooltip-direction="w" value="┏━━━┯━━━┯━━━┯━━━┯━━━┯━━━┯━━━┯━━━┯━━━┓
┃ 2 │ ~ │ ~ │ ~ │ ~ │ 2 │ ~ │ ~ │ ~ ┃
┠───┼───┼───┼───┼───┼───┼───┼───┼───┨
┃ ~ │ ~ │ 2 │ ~ │ ~ │ 2 │ ~ │ 2 │ ~ ┃
┠───┼───┼───┼───┼───┼───┼───┼───┼───┨
┃ ~ │ ~ │ ~ │ ~ │ ~ │ ~ │ 2 │ ~ │ ~ ┃
┠───┼───┼───┼───┼───┼───┼───┼───┼───┨
┃ 2 │ 2 │ ~ │ 2 │ 2 │ ~ │ ~ │ ~ │ 2 ┃
┠───┼───┼───┼───┼───┼───┼───┼───┼───┨
┃ ~ │ ~ │ ~ │ 2 │ ~ │ 2 │ ~ │ ~ │ ~ ┃
┠───┼───┼───┼───┼───┼───┼───┼───┼───┨
┃ ~ │ 2 │ ~ │ ~ │ ~ │ 2 │ 2 │ ~ │ ~ ┃
┠───┼───┼───┼───┼───┼───┼───┼───┼───┨
┃ ▼ │ ~ │ ~ │ 2 │ ~ │ ~ │ ~ │ ~ │ 2 ┃ Standing on height -1, carrying 0 ice blocks.
┗━━━┷━━━┷━━━┷━━━┷━━━┷━━━┷━━━┷━━━┷━━━┛" tabindex="0" role="button">
      <svg aria-hidden="true" height="16" viewBox="0 0 16 16" version="1.1" width="16" data-view-component="true" class="octicon octicon-copy js-clipboard-copy-icon m-2">
    <path fill-rule="evenodd" d="M0 6.75C0 5.784.784 5 1.75 5h1.5a.75.75 0 010 1.5h-1.5a.25.25 0 00-.25.25v7.5c0 .138.112.25.25.25h7.5a.25.25 0 00.25-.25v-1.5a.75.75 0 011.5 0v1.5A1.75 1.75 0 019.25 16h-7.5A1.75 1.75 0 010 14.25v-7.5z"></path><path fill-rule="evenodd" d="M5 1.75C5 .784 5.784 0 6.75 0h7.5C15.216 0 16 .784 16 1.75v7.5A1.75 1.75 0 0114.25 11h-7.5A1.75 1.75 0 015 9.25v-7.5zm1.75-.25a.25.25 0 00-.25.25v7.5c0 .138.112.25.25.25h7.5a.25.25 0 00.25-.25v-7.5a.25.25 0 00-.25-.25h-7.5z"></path>
</svg>
      <svg aria-hidden="true" height="16" viewBox="0 0 16 16" version="1.1" width="16" data-view-component="true" class="octicon octicon-check js-clipboard-check-icon color-fg-success d-none m-2">
    <path fill-rule="evenodd" d="M13.78 4.22a.75.75 0 010 1.06l-7.25 7.25a.75.75 0 01-1.06 0L2.22 9.28a.75.75 0 011.06-1.06L6 10.94l6.72-6.72a.75.75 0 011.06 0z"></path>
</svg>
    </clipboard-copy>
  </div></div>
<p dir="auto">The array for this:</p>
<div class="snippet-clipboard-content notranslate position-relative overflow-auto"><pre class="notranslate"><code>new int[][] { //
        { -1, -1, -1,  2, -1, -1,  2 }, //
        { -1,  2, -1,  2, -1, -1, -1 }, //
        { -1, -1, -1, -1, -1,  2, -1 }, //
        {  2, -1,  2,  2, -1, -1, -1 }, //
        { -1, -1, -1,  2, -1, -1, -1 }, //
        { -1,  2,  2, -1, -1,  2,  2 }, //
        { -1,  2, -1, -1,  2, -1, -1 }, //
        { -1, -1, -1, -1, -1,  2, -1 }, //
        {  2, -1, -1,  2, -1, -1, -1 }, //
}</code></pre><div class="zeroclipboard-container position-absolute right-0 top-0">
    <clipboard-copy aria-label="Copy" class="ClipboardButton btn js-clipboard-copy m-2 p-0 tooltipped-no-delay" data-copy-feedback="Copied!" data-tooltip-direction="w" value="new int[][] { //
        { -1, -1, -1,  2, -1, -1,  2 }, //
        { -1,  2, -1,  2, -1, -1, -1 }, //
        { -1, -1, -1, -1, -1,  2, -1 }, //
        {  2, -1,  2,  2, -1, -1, -1 }, //
        { -1, -1, -1,  2, -1, -1, -1 }, //
        { -1,  2,  2, -1, -1,  2,  2 }, //
        { -1,  2, -1, -1,  2, -1, -1 }, //
        { -1, -1, -1, -1, -1,  2, -1 }, //
        {  2, -1, -1,  2, -1, -1, -1 }, //
}" tabindex="0" role="button">
      <svg aria-hidden="true" height="16" viewBox="0 0 16 16" version="1.1" width="16" data-view-component="true" class="octicon octicon-copy js-clipboard-copy-icon m-2">
    <path fill-rule="evenodd" d="M0 6.75C0 5.784.784 5 1.75 5h1.5a.75.75 0 010 1.5h-1.5a.25.25 0 00-.25.25v7.5c0 .138.112.25.25.25h7.5a.25.25 0 00.25-.25v-1.5a.75.75 0 011.5 0v1.5A1.75 1.75 0 019.25 16h-7.5A1.75 1.75 0 010 14.25v-7.5z"></path><path fill-rule="evenodd" d="M5 1.75C5 .784 5.784 0 6.75 0h7.5C15.216 0 16 .784 16 1.75v7.5A1.75 1.75 0 0114.25 11h-7.5A1.75 1.75 0 015 9.25v-7.5zm1.75-.25a.25.25 0 00-.25.25v7.5c0 .138.112.25.25.25h7.5a.25.25 0 00.25-.25v-7.5a.25.25 0 00-.25-.25h-7.5z"></path>
</svg>
      <svg aria-hidden="true" height="16" viewBox="0 0 16 16" version="1.1" width="16" data-view-component="true" class="octicon octicon-check js-clipboard-check-icon color-fg-success d-none m-2">
    <path fill-rule="evenodd" d="M13.78 4.22a.75.75 0 010 1.06l-7.25 7.25a.75.75 0 01-1.06 0L2.22 9.28a.75.75 0 011.06-1.06L6 10.94l6.72-6.72a.75.75 0 011.06 0z"></path>
</svg>
    </clipboard-copy>
  </div></div>
</article>
