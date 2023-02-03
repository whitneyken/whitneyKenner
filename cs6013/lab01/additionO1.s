	.file	"addition.cpp"
# GNU C++14 (GCC) version 8.5.0 20210514 (Red Hat 8.5.0-15) (x86_64-redhat-linux)
#	compiled by GNU C version 8.5.0 20210514 (Red Hat 8.5.0-15), GMP version 6.1.2, MPFR version 3.1.6-p2, MPC version 1.1.0, isl version none
# GGC heuristics: --param ggc-min-expand=100 --param ggc-min-heapsize=131072
# options passed:  -D_GNU_SOURCE addition.cpp -mtune=generic -march=x86-64
# -auxbase-strip additionO1.s -g -O2 -fverbose-asm
# options enabled:  -faggressive-loop-optimizations -falign-labels
# -fasynchronous-unwind-tables -fauto-inc-dec -fbranch-count-reg
# -fcaller-saves -fchkp-check-incomplete-type -fchkp-check-read
# -fchkp-check-write -fchkp-instrument-calls -fchkp-narrow-bounds
# -fchkp-optimize -fchkp-store-bounds -fchkp-use-static-bounds
# -fchkp-use-static-const-bounds -fchkp-use-wrappers -fcode-hoisting
# -fcombine-stack-adjustments -fcommon -fcompare-elim -fcprop-registers
# -fcrossjumping -fcse-follow-jumps -fdefer-pop
# -fdelete-null-pointer-checks -fdevirtualize -fdevirtualize-speculatively
# -fdwarf2-cfi-asm -fearly-inlining -feliminate-unused-debug-types
# -fexceptions -fexpensive-optimizations -fforward-propagate
# -ffp-int-builtin-inexact -ffunction-cse -fgcse -fgcse-lm -fgnu-runtime
# -fgnu-unique -fguess-branch-probability -fhoist-adjacent-loads -fident
# -fif-conversion -fif-conversion2 -findirect-inlining -finline
# -finline-atomics -finline-functions-called-once -finline-small-functions
# -fipa-bit-cp -fipa-cp -fipa-icf -fipa-icf-functions -fipa-icf-variables
# -fipa-profile -fipa-pure-const -fipa-ra -fipa-reference -fipa-sra
# -fipa-vrp -fira-hoist-pressure -fira-share-save-slots
# -fira-share-spill-slots -fisolate-erroneous-paths-dereference -fivopts
# -fkeep-static-consts -fleading-underscore -flifetime-dse -flra-remat
# -flto-odr-type-merging -fmath-errno -fmerge-constants
# -fmerge-debug-strings -fmove-loop-invariants -fomit-frame-pointer
# -foptimize-sibling-calls -foptimize-strlen -fpartial-inlining -fpeephole
# -fpeephole2 -fplt -fprefetch-loop-arrays -free -freg-struct-return
# -freorder-blocks -freorder-blocks-and-partition -freorder-functions
# -frerun-cse-after-loop -fsched-critical-path-heuristic
# -fsched-dep-count-heuristic -fsched-group-heuristic -fsched-interblock
# -fsched-last-insn-heuristic -fsched-rank-heuristic -fsched-spec
# -fsched-spec-insn-heuristic -fsched-stalled-insns-dep -fschedule-fusion
# -fschedule-insns2 -fsemantic-interposition -fshow-column -fshrink-wrap
# -fshrink-wrap-separate -fsigned-zeros -fsplit-ivs-in-unroller
# -fsplit-wide-types -fssa-backprop -fssa-phiopt -fstdarg-opt
# -fstore-merging -fstrict-aliasing -fstrict-volatile-bitfields
# -fsync-libcalls -fthread-jumps -ftoplevel-reorder -ftrapping-math
# -ftree-bit-ccp -ftree-builtin-call-dce -ftree-ccp -ftree-ch
# -ftree-coalesce-vars -ftree-copy-prop -ftree-cselim -ftree-dce
# -ftree-dominator-opts -ftree-dse -ftree-forwprop -ftree-fre
# -ftree-loop-if-convert -ftree-loop-im -ftree-loop-ivcanon
# -ftree-loop-optimize -ftree-parallelize-loops= -ftree-phiprop -ftree-pre
# -ftree-pta -ftree-reassoc -ftree-scev-cprop -ftree-sink -ftree-slsr
# -ftree-sra -ftree-switch-conversion -ftree-tail-merge -ftree-ter
# -ftree-vrp -funit-at-a-time -funwind-tables -fvar-tracking
# -fvar-tracking-assignments -fverbose-asm -fzero-initialized-in-bss
# -m128bit-long-double -m64 -m80387 -malign-stringops
# -mavx256-split-unaligned-load -mavx256-split-unaligned-store
# -mfancy-math-387 -mfp-ret-in-387 -mfxsr -mglibc -mieee-fp
# -mlong-double-80 -mmmx -mno-sse4 -mpush-args -mred-zone -msse -msse2
# -mstv -mtls-direct-seg-refs -mvzeroupper

	.text
.Ltext0:
	.section	.rodata.str1.1,"aMS",@progbits,1
.LC0:
	.string	"%d"
	.section	.text.startup,"ax",@progbits
	.p2align 4,,15
	.globl	main
	.type	main, @function
main:
.LVL0:
.LFB13:
	.file 1 "addition.cpp"
	.loc 1 8 41 view -0
	.cfi_startproc
	.loc 1 9 1 view .LVU1
# addition.cpp:8: int main(int argc, const char * argv[]) {
	.loc 1 8 41 is_stmt 0 view .LVU2
	subq	$8, %rsp	#,
	.cfi_def_cfa_offset 16
# addition.cpp:9: printf("%d", add(3, 1));
	.loc 1 9 7 view .LVU3
	movl	$4, %esi	#,
.LVL1:
	.loc 1 9 7 view .LVU4
	movl	$.LC0, %edi	#,
.LVL2:
	.loc 1 9 7 view .LVU5
	xorl	%eax, %eax	#
	call	printf	#
.LVL3:
	.loc 1 10 1 is_stmt 1 view .LVU6
# addition.cpp:11: }
	.loc 1 11 1 is_stmt 0 view .LVU7
	xorl	%eax, %eax	#
	addq	$8, %rsp	#,
	.cfi_def_cfa_offset 8
	ret	
	.cfi_endproc
.LFE13:
	.size	main, .-main
	.text
.Letext0:
	.file 2 "/usr/include/c++/8/cstdio"
	.file 3 "/usr/include/c++/8/x86_64-redhat-linux/bits/c++config.h"
	.file 4 "/usr/lib/gcc/x86_64-redhat-linux/8/include/stddef.h"
	.file 5 "/usr/include/bits/types.h"
	.file 6 "/usr/include/bits/types/__mbstate_t.h"
	.file 7 "/usr/include/bits/types/__fpos_t.h"
	.file 8 "/usr/include/bits/types/struct_FILE.h"
	.file 9 "/usr/include/bits/types/FILE.h"
	.file 10 "/usr/include/stdio.h"
	.file 11 "/usr/include/bits/sys_errlist.h"
	.file 12 "/usr/include/bits/stdio.h"
	.file 13 "<built-in>"
	.section	.debug_info,"",@progbits
.Ldebug_info0:
	.long	0x7d6
	.value	0x4
	.long	.Ldebug_abbrev0
	.byte	0x8
	.uleb128 0x1
	.long	.LASF90
	.byte	0x4
	.long	.LASF91
	.long	.LASF92
	.long	.Ldebug_ranges0+0
	.quad	0
	.long	.Ldebug_line0
	.uleb128 0x2
	.string	"std"
	.byte	0xd
	.byte	0
	.long	0x11f
	.uleb128 0x3
	.long	.LASF0
	.byte	0x3
	.value	0x8ae
	.byte	0x41
	.uleb128 0x4
	.byte	0x3
	.value	0x8ae
	.byte	0x41
	.long	0x34
	.uleb128 0x5
	.byte	0x2
	.byte	0x62
	.byte	0xb
	.long	0x3d0
	.uleb128 0x5
	.byte	0x2
	.byte	0x63
	.byte	0xb
	.long	0x43c
	.uleb128 0x5
	.byte	0x2
	.byte	0x65
	.byte	0xb
	.long	0x4b2
	.uleb128 0x5
	.byte	0x2
	.byte	0x66
	.byte	0xb
	.long	0x4c5
	.uleb128 0x5
	.byte	0x2
	.byte	0x67
	.byte	0xb
	.long	0x4db
	.uleb128 0x5
	.byte	0x2
	.byte	0x68
	.byte	0xb
	.long	0x4f2
	.uleb128 0x5
	.byte	0x2
	.byte	0x69
	.byte	0xb
	.long	0x509
	.uleb128 0x5
	.byte	0x2
	.byte	0x6a
	.byte	0xb
	.long	0x51f
	.uleb128 0x5
	.byte	0x2
	.byte	0x6b
	.byte	0xb
	.long	0x536
	.uleb128 0x5
	.byte	0x2
	.byte	0x6c
	.byte	0xb
	.long	0x558
	.uleb128 0x5
	.byte	0x2
	.byte	0x6d
	.byte	0xb
	.long	0x579
	.uleb128 0x5
	.byte	0x2
	.byte	0x71
	.byte	0xb
	.long	0x594
	.uleb128 0x5
	.byte	0x2
	.byte	0x72
	.byte	0xb
	.long	0x5ba
	.uleb128 0x5
	.byte	0x2
	.byte	0x74
	.byte	0xb
	.long	0x5da
	.uleb128 0x5
	.byte	0x2
	.byte	0x75
	.byte	0xb
	.long	0x5fb
	.uleb128 0x5
	.byte	0x2
	.byte	0x76
	.byte	0xb
	.long	0x61d
	.uleb128 0x5
	.byte	0x2
	.byte	0x78
	.byte	0xb
	.long	0x634
	.uleb128 0x5
	.byte	0x2
	.byte	0x79
	.byte	0xb
	.long	0x64b
	.uleb128 0x5
	.byte	0x2
	.byte	0x7e
	.byte	0xb
	.long	0x657
	.uleb128 0x5
	.byte	0x2
	.byte	0x83
	.byte	0xb
	.long	0x66a
	.uleb128 0x5
	.byte	0x2
	.byte	0x84
	.byte	0xb
	.long	0x680
	.uleb128 0x5
	.byte	0x2
	.byte	0x85
	.byte	0xb
	.long	0x69b
	.uleb128 0x5
	.byte	0x2
	.byte	0x87
	.byte	0xb
	.long	0x6ae
	.uleb128 0x5
	.byte	0x2
	.byte	0x88
	.byte	0xb
	.long	0x6c6
	.uleb128 0x5
	.byte	0x2
	.byte	0x8b
	.byte	0xb
	.long	0x6ec
	.uleb128 0x5
	.byte	0x2
	.byte	0x8d
	.byte	0xb
	.long	0x6f8
	.uleb128 0x5
	.byte	0x2
	.byte	0x8f
	.byte	0xb
	.long	0x70e
	.byte	0
	.uleb128 0x6
	.long	.LASF93
	.byte	0x3
	.value	0x8b0
	.byte	0xb
	.long	0x13f
	.uleb128 0x3
	.long	.LASF0
	.byte	0x3
	.value	0x8b2
	.byte	0x41
	.uleb128 0x4
	.byte	0x3
	.value	0x8b2
	.byte	0x41
	.long	0x12c
	.byte	0
	.uleb128 0x7
	.long	.LASF8
	.byte	0x4
	.byte	0xd8
	.byte	0x17
	.long	0x14b
	.uleb128 0x8
	.byte	0x8
	.byte	0x7
	.long	.LASF1
	.uleb128 0x8
	.byte	0x4
	.byte	0x7
	.long	.LASF2
	.uleb128 0x9
	.byte	0x8
	.uleb128 0x8
	.byte	0x1
	.byte	0x8
	.long	.LASF3
	.uleb128 0x8
	.byte	0x2
	.byte	0x7
	.long	.LASF4
	.uleb128 0x8
	.byte	0x1
	.byte	0x6
	.long	.LASF5
	.uleb128 0x8
	.byte	0x2
	.byte	0x5
	.long	.LASF6
	.uleb128 0xa
	.byte	0x4
	.byte	0x5
	.string	"int"
	.uleb128 0x8
	.byte	0x8
	.byte	0x5
	.long	.LASF7
	.uleb128 0x7
	.long	.LASF9
	.byte	0x5
	.byte	0x96
	.byte	0x19
	.long	0x17e
	.uleb128 0x7
	.long	.LASF10
	.byte	0x5
	.byte	0x97
	.byte	0x1b
	.long	0x17e
	.uleb128 0xb
	.byte	0x8
	.long	0x1a3
	.uleb128 0x8
	.byte	0x1
	.byte	0x6
	.long	.LASF11
	.uleb128 0xc
	.long	0x1a3
	.uleb128 0xd
	.byte	0x8
	.byte	0x6
	.byte	0xe
	.byte	0x1
	.long	.LASF94
	.long	0x1f9
	.uleb128 0xe
	.byte	0x4
	.byte	0x6
	.byte	0x11
	.byte	0x3
	.long	0x1de
	.uleb128 0xf
	.long	.LASF12
	.byte	0x6
	.byte	0x12
	.byte	0x13
	.long	0x152
	.uleb128 0xf
	.long	.LASF13
	.byte	0x6
	.byte	0x13
	.byte	0x12
	.long	0x1f9
	.byte	0
	.uleb128 0x10
	.long	.LASF14
	.byte	0x6
	.byte	0xf
	.byte	0x7
	.long	0x177
	.byte	0
	.uleb128 0x10
	.long	.LASF15
	.byte	0x6
	.byte	0x14
	.byte	0x5
	.long	0x1bc
	.byte	0x4
	.byte	0
	.uleb128 0x11
	.long	0x1a3
	.long	0x209
	.uleb128 0x12
	.long	0x14b
	.byte	0x3
	.byte	0
	.uleb128 0x7
	.long	.LASF16
	.byte	0x6
	.byte	0x15
	.byte	0x3
	.long	0x1af
	.uleb128 0x13
	.long	.LASF20
	.byte	0x10
	.byte	0x7
	.byte	0xa
	.byte	0x10
	.long	0x23d
	.uleb128 0x10
	.long	.LASF17
	.byte	0x7
	.byte	0xc
	.byte	0xb
	.long	0x185
	.byte	0
	.uleb128 0x10
	.long	.LASF18
	.byte	0x7
	.byte	0xd
	.byte	0xf
	.long	0x209
	.byte	0x8
	.byte	0
	.uleb128 0x7
	.long	.LASF19
	.byte	0x7
	.byte	0xe
	.byte	0x3
	.long	0x215
	.uleb128 0x13
	.long	.LASF21
	.byte	0xd8
	.byte	0x8
	.byte	0x31
	.byte	0x8
	.long	0x3d0
	.uleb128 0x10
	.long	.LASF22
	.byte	0x8
	.byte	0x33
	.byte	0x7
	.long	0x177
	.byte	0
	.uleb128 0x10
	.long	.LASF23
	.byte	0x8
	.byte	0x36
	.byte	0x9
	.long	0x19d
	.byte	0x8
	.uleb128 0x10
	.long	.LASF24
	.byte	0x8
	.byte	0x37
	.byte	0x9
	.long	0x19d
	.byte	0x10
	.uleb128 0x10
	.long	.LASF25
	.byte	0x8
	.byte	0x38
	.byte	0x9
	.long	0x19d
	.byte	0x18
	.uleb128 0x10
	.long	.LASF26
	.byte	0x8
	.byte	0x39
	.byte	0x9
	.long	0x19d
	.byte	0x20
	.uleb128 0x10
	.long	.LASF27
	.byte	0x8
	.byte	0x3a
	.byte	0x9
	.long	0x19d
	.byte	0x28
	.uleb128 0x10
	.long	.LASF28
	.byte	0x8
	.byte	0x3b
	.byte	0x9
	.long	0x19d
	.byte	0x30
	.uleb128 0x10
	.long	.LASF29
	.byte	0x8
	.byte	0x3c
	.byte	0x9
	.long	0x19d
	.byte	0x38
	.uleb128 0x10
	.long	.LASF30
	.byte	0x8
	.byte	0x3d
	.byte	0x9
	.long	0x19d
	.byte	0x40
	.uleb128 0x10
	.long	.LASF31
	.byte	0x8
	.byte	0x40
	.byte	0x9
	.long	0x19d
	.byte	0x48
	.uleb128 0x10
	.long	.LASF32
	.byte	0x8
	.byte	0x41
	.byte	0x9
	.long	0x19d
	.byte	0x50
	.uleb128 0x10
	.long	.LASF33
	.byte	0x8
	.byte	0x42
	.byte	0x9
	.long	0x19d
	.byte	0x58
	.uleb128 0x10
	.long	.LASF34
	.byte	0x8
	.byte	0x44
	.byte	0x16
	.long	0x3e9
	.byte	0x60
	.uleb128 0x10
	.long	.LASF35
	.byte	0x8
	.byte	0x46
	.byte	0x14
	.long	0x3ef
	.byte	0x68
	.uleb128 0x10
	.long	.LASF36
	.byte	0x8
	.byte	0x48
	.byte	0x7
	.long	0x177
	.byte	0x70
	.uleb128 0x10
	.long	.LASF37
	.byte	0x8
	.byte	0x49
	.byte	0x7
	.long	0x177
	.byte	0x74
	.uleb128 0x10
	.long	.LASF38
	.byte	0x8
	.byte	0x4a
	.byte	0xb
	.long	0x185
	.byte	0x78
	.uleb128 0x10
	.long	.LASF39
	.byte	0x8
	.byte	0x4d
	.byte	0x12
	.long	0x162
	.byte	0x80
	.uleb128 0x10
	.long	.LASF40
	.byte	0x8
	.byte	0x4e
	.byte	0xf
	.long	0x169
	.byte	0x82
	.uleb128 0x10
	.long	.LASF41
	.byte	0x8
	.byte	0x4f
	.byte	0x13
	.long	0x3f5
	.byte	0x83
	.uleb128 0x10
	.long	.LASF42
	.byte	0x8
	.byte	0x51
	.byte	0xf
	.long	0x405
	.byte	0x88
	.uleb128 0x10
	.long	.LASF43
	.byte	0x8
	.byte	0x59
	.byte	0xd
	.long	0x191
	.byte	0x90
	.uleb128 0x10
	.long	.LASF44
	.byte	0x8
	.byte	0x5b
	.byte	0x17
	.long	0x410
	.byte	0x98
	.uleb128 0x10
	.long	.LASF45
	.byte	0x8
	.byte	0x5c
	.byte	0x19
	.long	0x41b
	.byte	0xa0
	.uleb128 0x10
	.long	.LASF46
	.byte	0x8
	.byte	0x5d
	.byte	0x14
	.long	0x3ef
	.byte	0xa8
	.uleb128 0x10
	.long	.LASF47
	.byte	0x8
	.byte	0x5e
	.byte	0x9
	.long	0x159
	.byte	0xb0
	.uleb128 0x10
	.long	.LASF48
	.byte	0x8
	.byte	0x5f
	.byte	0xa
	.long	0x13f
	.byte	0xb8
	.uleb128 0x10
	.long	.LASF49
	.byte	0x8
	.byte	0x60
	.byte	0x7
	.long	0x177
	.byte	0xc0
	.uleb128 0x10
	.long	.LASF50
	.byte	0x8
	.byte	0x62
	.byte	0x4a
	.long	0x421
	.byte	0xc4
	.byte	0
	.uleb128 0x7
	.long	.LASF51
	.byte	0x9
	.byte	0x7
	.byte	0x19
	.long	0x249
	.uleb128 0x14
	.long	.LASF95
	.byte	0x8
	.byte	0x2b
	.byte	0xe
	.uleb128 0x15
	.long	.LASF52
	.uleb128 0xb
	.byte	0x8
	.long	0x3e4
	.uleb128 0xb
	.byte	0x8
	.long	0x249
	.uleb128 0x11
	.long	0x1a3
	.long	0x405
	.uleb128 0x12
	.long	0x14b
	.byte	0
	.byte	0
	.uleb128 0xb
	.byte	0x8
	.long	0x3dc
	.uleb128 0x15
	.long	.LASF53
	.uleb128 0xb
	.byte	0x8
	.long	0x40b
	.uleb128 0x15
	.long	.LASF54
	.uleb128 0xb
	.byte	0x8
	.long	0x416
	.uleb128 0x11
	.long	0x1a3
	.long	0x431
	.uleb128 0x12
	.long	0x14b
	.byte	0x13
	.byte	0
	.uleb128 0xb
	.byte	0x8
	.long	0x1aa
	.uleb128 0xc
	.long	0x431
	.uleb128 0x7
	.long	.LASF55
	.byte	0xa
	.byte	0x54
	.byte	0x12
	.long	0x23d
	.uleb128 0xc
	.long	0x43c
	.uleb128 0x16
	.long	.LASF56
	.byte	0xa
	.byte	0x89
	.byte	0xe
	.long	0x459
	.uleb128 0xb
	.byte	0x8
	.long	0x3d0
	.uleb128 0x16
	.long	.LASF57
	.byte	0xa
	.byte	0x8a
	.byte	0xe
	.long	0x459
	.uleb128 0x16
	.long	.LASF58
	.byte	0xa
	.byte	0x8b
	.byte	0xe
	.long	0x459
	.uleb128 0x16
	.long	.LASF59
	.byte	0xb
	.byte	0x1a
	.byte	0xc
	.long	0x177
	.uleb128 0x11
	.long	0x437
	.long	0x48e
	.uleb128 0x17
	.byte	0
	.uleb128 0x16
	.long	.LASF60
	.byte	0xb
	.byte	0x1b
	.byte	0x1a
	.long	0x483
	.uleb128 0x16
	.long	.LASF61
	.byte	0xb
	.byte	0x1e
	.byte	0xc
	.long	0x177
	.uleb128 0x16
	.long	.LASF62
	.byte	0xb
	.byte	0x1f
	.byte	0x1a
	.long	0x483
	.uleb128 0x18
	.long	.LASF77
	.byte	0xa
	.value	0x2fb
	.byte	0xd
	.long	0x4c5
	.uleb128 0x19
	.long	0x459
	.byte	0
	.uleb128 0x1a
	.long	.LASF63
	.byte	0xa
	.byte	0xd5
	.byte	0xc
	.long	0x177
	.long	0x4db
	.uleb128 0x19
	.long	0x459
	.byte	0
	.uleb128 0x1b
	.long	.LASF64
	.byte	0xa
	.value	0x2fd
	.byte	0xc
	.long	0x177
	.long	0x4f2
	.uleb128 0x19
	.long	0x459
	.byte	0
	.uleb128 0x1b
	.long	.LASF65
	.byte	0xa
	.value	0x2ff
	.byte	0xc
	.long	0x177
	.long	0x509
	.uleb128 0x19
	.long	0x459
	.byte	0
	.uleb128 0x1a
	.long	.LASF66
	.byte	0xa
	.byte	0xda
	.byte	0xc
	.long	0x177
	.long	0x51f
	.uleb128 0x19
	.long	0x459
	.byte	0
	.uleb128 0x1b
	.long	.LASF67
	.byte	0xa
	.value	0x1eb
	.byte	0xc
	.long	0x177
	.long	0x536
	.uleb128 0x19
	.long	0x459
	.byte	0
	.uleb128 0x1b
	.long	.LASF68
	.byte	0xa
	.value	0x2e1
	.byte	0xc
	.long	0x177
	.long	0x552
	.uleb128 0x19
	.long	0x459
	.uleb128 0x19
	.long	0x552
	.byte	0
	.uleb128 0xb
	.byte	0x8
	.long	0x43c
	.uleb128 0x1b
	.long	.LASF69
	.byte	0xa
	.value	0x23a
	.byte	0xe
	.long	0x19d
	.long	0x579
	.uleb128 0x19
	.long	0x19d
	.uleb128 0x19
	.long	0x177
	.uleb128 0x19
	.long	0x459
	.byte	0
	.uleb128 0x1a
	.long	.LASF70
	.byte	0xa
	.byte	0xf6
	.byte	0xe
	.long	0x459
	.long	0x594
	.uleb128 0x19
	.long	0x431
	.uleb128 0x19
	.long	0x431
	.byte	0
	.uleb128 0x1b
	.long	.LASF71
	.byte	0xa
	.value	0x28c
	.byte	0xf
	.long	0x13f
	.long	0x5ba
	.uleb128 0x19
	.long	0x159
	.uleb128 0x19
	.long	0x13f
	.uleb128 0x19
	.long	0x13f
	.uleb128 0x19
	.long	0x459
	.byte	0
	.uleb128 0x1a
	.long	.LASF72
	.byte	0xa
	.byte	0xfc
	.byte	0xe
	.long	0x459
	.long	0x5da
	.uleb128 0x19
	.long	0x431
	.uleb128 0x19
	.long	0x431
	.uleb128 0x19
	.long	0x459
	.byte	0
	.uleb128 0x1b
	.long	.LASF73
	.byte	0xa
	.value	0x2b2
	.byte	0xc
	.long	0x177
	.long	0x5fb
	.uleb128 0x19
	.long	0x459
	.uleb128 0x19
	.long	0x17e
	.uleb128 0x19
	.long	0x177
	.byte	0
	.uleb128 0x1b
	.long	.LASF74
	.byte	0xa
	.value	0x2e6
	.byte	0xc
	.long	0x177
	.long	0x617
	.uleb128 0x19
	.long	0x459
	.uleb128 0x19
	.long	0x617
	.byte	0
	.uleb128 0xb
	.byte	0x8
	.long	0x448
	.uleb128 0x1b
	.long	.LASF75
	.byte	0xa
	.value	0x2b7
	.byte	0x11
	.long	0x17e
	.long	0x634
	.uleb128 0x19
	.long	0x459
	.byte	0
	.uleb128 0x1b
	.long	.LASF76
	.byte	0xa
	.value	0x1ec
	.byte	0xc
	.long	0x177
	.long	0x64b
	.uleb128 0x19
	.long	0x459
	.byte	0
	.uleb128 0x1c
	.long	.LASF84
	.byte	0xc
	.byte	0x2f
	.byte	0x1
	.long	0x177
	.uleb128 0x18
	.long	.LASF78
	.byte	0xa
	.value	0x30d
	.byte	0xd
	.long	0x66a
	.uleb128 0x19
	.long	0x431
	.byte	0
	.uleb128 0x1a
	.long	.LASF79
	.byte	0xa
	.byte	0x92
	.byte	0xc
	.long	0x177
	.long	0x680
	.uleb128 0x19
	.long	0x431
	.byte	0
	.uleb128 0x1a
	.long	.LASF80
	.byte	0xa
	.byte	0x94
	.byte	0xc
	.long	0x177
	.long	0x69b
	.uleb128 0x19
	.long	0x431
	.uleb128 0x19
	.long	0x431
	.byte	0
	.uleb128 0x18
	.long	.LASF81
	.byte	0xa
	.value	0x2bc
	.byte	0xd
	.long	0x6ae
	.uleb128 0x19
	.long	0x459
	.byte	0
	.uleb128 0x18
	.long	.LASF82
	.byte	0xa
	.value	0x130
	.byte	0xd
	.long	0x6c6
	.uleb128 0x19
	.long	0x459
	.uleb128 0x19
	.long	0x19d
	.byte	0
	.uleb128 0x1b
	.long	.LASF83
	.byte	0xa
	.value	0x134
	.byte	0xc
	.long	0x177
	.long	0x6ec
	.uleb128 0x19
	.long	0x459
	.uleb128 0x19
	.long	0x19d
	.uleb128 0x19
	.long	0x177
	.uleb128 0x19
	.long	0x13f
	.byte	0
	.uleb128 0x1c
	.long	.LASF85
	.byte	0xa
	.byte	0xad
	.byte	0xe
	.long	0x459
	.uleb128 0x1a
	.long	.LASF86
	.byte	0xa
	.byte	0xbb
	.byte	0xe
	.long	0x19d
	.long	0x70e
	.uleb128 0x19
	.long	0x19d
	.byte	0
	.uleb128 0x1b
	.long	.LASF87
	.byte	0xa
	.value	0x285
	.byte	0xc
	.long	0x177
	.long	0x72a
	.uleb128 0x19
	.long	0x177
	.uleb128 0x19
	.long	0x459
	.byte	0
	.uleb128 0x1d
	.long	.LASF96
	.byte	0x1
	.byte	0x4
	.byte	0x3
	.long	.LASF97
	.long	0x177
	.byte	0x1
	.long	0x75b
	.uleb128 0x1e
	.string	"T"
	.long	0x177
	.uleb128 0x1f
	.string	"a"
	.byte	0x1
	.byte	0x4
	.byte	0x9
	.long	0x177
	.uleb128 0x1f
	.string	"b"
	.byte	0x1
	.byte	0x4
	.byte	0xe
	.long	0x177
	.byte	0
	.uleb128 0x20
	.long	.LASF98
	.byte	0x1
	.byte	0x8
	.byte	0x5
	.long	0x177
	.quad	.LFB13
	.quad	.LFE13-.LFB13
	.uleb128 0x1
	.byte	0x9c
	.long	0x7c6
	.uleb128 0x21
	.long	.LASF88
	.byte	0x1
	.byte	0x8
	.byte	0xe
	.long	0x177
	.long	.LLST0
	.long	.LVUS0
	.uleb128 0x21
	.long	.LASF89
	.byte	0x1
	.byte	0x8
	.byte	0x21
	.long	0x7c6
	.long	.LLST1
	.long	.LVUS1
	.uleb128 0x22
	.quad	.LVL3
	.long	0x7cc
	.uleb128 0x23
	.uleb128 0x1
	.byte	0x55
	.uleb128 0x9
	.byte	0x3
	.quad	.LC0
	.uleb128 0x23
	.uleb128 0x1
	.byte	0x54
	.uleb128 0x1
	.byte	0x34
	.byte	0
	.byte	0
	.uleb128 0xb
	.byte	0x8
	.long	0x431
	.uleb128 0x24
	.long	.LASF99
	.long	.LASF99
	.byte	0xa
	.value	0x14c
	.byte	0xc
	.byte	0
	.section	.debug_abbrev,"",@progbits
.Ldebug_abbrev0:
	.uleb128 0x1
	.uleb128 0x11
	.byte	0x1
	.uleb128 0x25
	.uleb128 0xe
	.uleb128 0x13
	.uleb128 0xb
	.uleb128 0x3
	.uleb128 0xe
	.uleb128 0x1b
	.uleb128 0xe
	.uleb128 0x55
	.uleb128 0x17
	.uleb128 0x11
	.uleb128 0x1
	.uleb128 0x10
	.uleb128 0x17
	.byte	0
	.byte	0
	.uleb128 0x2
	.uleb128 0x39
	.byte	0x1
	.uleb128 0x3
	.uleb128 0x8
	.uleb128 0x3a
	.uleb128 0xb
	.uleb128 0x3b
	.uleb128 0xb
	.uleb128 0x1
	.uleb128 0x13
	.byte	0
	.byte	0
	.uleb128 0x3
	.uleb128 0x39
	.byte	0
	.uleb128 0x3
	.uleb128 0xe
	.uleb128 0x3a
	.uleb128 0xb
	.uleb128 0x3b
	.uleb128 0x5
	.uleb128 0x39
	.uleb128 0xb
	.uleb128 0x89
	.uleb128 0x19
	.byte	0
	.byte	0
	.uleb128 0x4
	.uleb128 0x3a
	.byte	0
	.uleb128 0x3a
	.uleb128 0xb
	.uleb128 0x3b
	.uleb128 0x5
	.uleb128 0x39
	.uleb128 0xb
	.uleb128 0x18
	.uleb128 0x13
	.byte	0
	.byte	0
	.uleb128 0x5
	.uleb128 0x8
	.byte	0
	.uleb128 0x3a
	.uleb128 0xb
	.uleb128 0x3b
	.uleb128 0xb
	.uleb128 0x39
	.uleb128 0xb
	.uleb128 0x18
	.uleb128 0x13
	.byte	0
	.byte	0
	.uleb128 0x6
	.uleb128 0x39
	.byte	0x1
	.uleb128 0x3
	.uleb128 0xe
	.uleb128 0x3a
	.uleb128 0xb
	.uleb128 0x3b
	.uleb128 0x5
	.uleb128 0x39
	.uleb128 0xb
	.uleb128 0x1
	.uleb128 0x13
	.byte	0
	.byte	0
	.uleb128 0x7
	.uleb128 0x16
	.byte	0
	.uleb128 0x3
	.uleb128 0xe
	.uleb128 0x3a
	.uleb128 0xb
	.uleb128 0x3b
	.uleb128 0xb
	.uleb128 0x39
	.uleb128 0xb
	.uleb128 0x49
	.uleb128 0x13
	.byte	0
	.byte	0
	.uleb128 0x8
	.uleb128 0x24
	.byte	0
	.uleb128 0xb
	.uleb128 0xb
	.uleb128 0x3e
	.uleb128 0xb
	.uleb128 0x3
	.uleb128 0xe
	.byte	0
	.byte	0
	.uleb128 0x9
	.uleb128 0xf
	.byte	0
	.uleb128 0xb
	.uleb128 0xb
	.byte	0
	.byte	0
	.uleb128 0xa
	.uleb128 0x24
	.byte	0
	.uleb128 0xb
	.uleb128 0xb
	.uleb128 0x3e
	.uleb128 0xb
	.uleb128 0x3
	.uleb128 0x8
	.byte	0
	.byte	0
	.uleb128 0xb
	.uleb128 0xf
	.byte	0
	.uleb128 0xb
	.uleb128 0xb
	.uleb128 0x49
	.uleb128 0x13
	.byte	0
	.byte	0
	.uleb128 0xc
	.uleb128 0x26
	.byte	0
	.uleb128 0x49
	.uleb128 0x13
	.byte	0
	.byte	0
	.uleb128 0xd
	.uleb128 0x13
	.byte	0x1
	.uleb128 0xb
	.uleb128 0xb
	.uleb128 0x3a
	.uleb128 0xb
	.uleb128 0x3b
	.uleb128 0xb
	.uleb128 0x39
	.uleb128 0xb
	.uleb128 0x6e
	.uleb128 0xe
	.uleb128 0x1
	.uleb128 0x13
	.byte	0
	.byte	0
	.uleb128 0xe
	.uleb128 0x17
	.byte	0x1
	.uleb128 0xb
	.uleb128 0xb
	.uleb128 0x3a
	.uleb128 0xb
	.uleb128 0x3b
	.uleb128 0xb
	.uleb128 0x39
	.uleb128 0xb
	.uleb128 0x1
	.uleb128 0x13
	.byte	0
	.byte	0
	.uleb128 0xf
	.uleb128 0xd
	.byte	0
	.uleb128 0x3
	.uleb128 0xe
	.uleb128 0x3a
	.uleb128 0xb
	.uleb128 0x3b
	.uleb128 0xb
	.uleb128 0x39
	.uleb128 0xb
	.uleb128 0x49
	.uleb128 0x13
	.byte	0
	.byte	0
	.uleb128 0x10
	.uleb128 0xd
	.byte	0
	.uleb128 0x3
	.uleb128 0xe
	.uleb128 0x3a
	.uleb128 0xb
	.uleb128 0x3b
	.uleb128 0xb
	.uleb128 0x39
	.uleb128 0xb
	.uleb128 0x49
	.uleb128 0x13
	.uleb128 0x38
	.uleb128 0xb
	.byte	0
	.byte	0
	.uleb128 0x11
	.uleb128 0x1
	.byte	0x1
	.uleb128 0x49
	.uleb128 0x13
	.uleb128 0x1
	.uleb128 0x13
	.byte	0
	.byte	0
	.uleb128 0x12
	.uleb128 0x21
	.byte	0
	.uleb128 0x49
	.uleb128 0x13
	.uleb128 0x2f
	.uleb128 0xb
	.byte	0
	.byte	0
	.uleb128 0x13
	.uleb128 0x13
	.byte	0x1
	.uleb128 0x3
	.uleb128 0xe
	.uleb128 0xb
	.uleb128 0xb
	.uleb128 0x3a
	.uleb128 0xb
	.uleb128 0x3b
	.uleb128 0xb
	.uleb128 0x39
	.uleb128 0xb
	.uleb128 0x1
	.uleb128 0x13
	.byte	0
	.byte	0
	.uleb128 0x14
	.uleb128 0x16
	.byte	0
	.uleb128 0x3
	.uleb128 0xe
	.uleb128 0x3a
	.uleb128 0xb
	.uleb128 0x3b
	.uleb128 0xb
	.uleb128 0x39
	.uleb128 0xb
	.byte	0
	.byte	0
	.uleb128 0x15
	.uleb128 0x13
	.byte	0
	.uleb128 0x3
	.uleb128 0xe
	.uleb128 0x3c
	.uleb128 0x19
	.byte	0
	.byte	0
	.uleb128 0x16
	.uleb128 0x34
	.byte	0
	.uleb128 0x3
	.uleb128 0xe
	.uleb128 0x3a
	.uleb128 0xb
	.uleb128 0x3b
	.uleb128 0xb
	.uleb128 0x39
	.uleb128 0xb
	.uleb128 0x49
	.uleb128 0x13
	.uleb128 0x3f
	.uleb128 0x19
	.uleb128 0x3c
	.uleb128 0x19
	.byte	0
	.byte	0
	.uleb128 0x17
	.uleb128 0x21
	.byte	0
	.byte	0
	.byte	0
	.uleb128 0x18
	.uleb128 0x2e
	.byte	0x1
	.uleb128 0x3f
	.uleb128 0x19
	.uleb128 0x3
	.uleb128 0xe
	.uleb128 0x3a
	.uleb128 0xb
	.uleb128 0x3b
	.uleb128 0x5
	.uleb128 0x39
	.uleb128 0xb
	.uleb128 0x3c
	.uleb128 0x19
	.uleb128 0x1
	.uleb128 0x13
	.byte	0
	.byte	0
	.uleb128 0x19
	.uleb128 0x5
	.byte	0
	.uleb128 0x49
	.uleb128 0x13
	.byte	0
	.byte	0
	.uleb128 0x1a
	.uleb128 0x2e
	.byte	0x1
	.uleb128 0x3f
	.uleb128 0x19
	.uleb128 0x3
	.uleb128 0xe
	.uleb128 0x3a
	.uleb128 0xb
	.uleb128 0x3b
	.uleb128 0xb
	.uleb128 0x39
	.uleb128 0xb
	.uleb128 0x49
	.uleb128 0x13
	.uleb128 0x3c
	.uleb128 0x19
	.uleb128 0x1
	.uleb128 0x13
	.byte	0
	.byte	0
	.uleb128 0x1b
	.uleb128 0x2e
	.byte	0x1
	.uleb128 0x3f
	.uleb128 0x19
	.uleb128 0x3
	.uleb128 0xe
	.uleb128 0x3a
	.uleb128 0xb
	.uleb128 0x3b
	.uleb128 0x5
	.uleb128 0x39
	.uleb128 0xb
	.uleb128 0x49
	.uleb128 0x13
	.uleb128 0x3c
	.uleb128 0x19
	.uleb128 0x1
	.uleb128 0x13
	.byte	0
	.byte	0
	.uleb128 0x1c
	.uleb128 0x2e
	.byte	0
	.uleb128 0x3f
	.uleb128 0x19
	.uleb128 0x3
	.uleb128 0xe
	.uleb128 0x3a
	.uleb128 0xb
	.uleb128 0x3b
	.uleb128 0xb
	.uleb128 0x39
	.uleb128 0xb
	.uleb128 0x49
	.uleb128 0x13
	.uleb128 0x3c
	.uleb128 0x19
	.byte	0
	.byte	0
	.uleb128 0x1d
	.uleb128 0x2e
	.byte	0x1
	.uleb128 0x3f
	.uleb128 0x19
	.uleb128 0x3
	.uleb128 0xe
	.uleb128 0x3a
	.uleb128 0xb
	.uleb128 0x3b
	.uleb128 0xb
	.uleb128 0x39
	.uleb128 0xb
	.uleb128 0x6e
	.uleb128 0xe
	.uleb128 0x49
	.uleb128 0x13
	.uleb128 0x20
	.uleb128 0xb
	.uleb128 0x1
	.uleb128 0x13
	.byte	0
	.byte	0
	.uleb128 0x1e
	.uleb128 0x2f
	.byte	0
	.uleb128 0x3
	.uleb128 0x8
	.uleb128 0x49
	.uleb128 0x13
	.byte	0
	.byte	0
	.uleb128 0x1f
	.uleb128 0x5
	.byte	0
	.uleb128 0x3
	.uleb128 0x8
	.uleb128 0x3a
	.uleb128 0xb
	.uleb128 0x3b
	.uleb128 0xb
	.uleb128 0x39
	.uleb128 0xb
	.uleb128 0x49
	.uleb128 0x13
	.byte	0
	.byte	0
	.uleb128 0x20
	.uleb128 0x2e
	.byte	0x1
	.uleb128 0x3f
	.uleb128 0x19
	.uleb128 0x3
	.uleb128 0xe
	.uleb128 0x3a
	.uleb128 0xb
	.uleb128 0x3b
	.uleb128 0xb
	.uleb128 0x39
	.uleb128 0xb
	.uleb128 0x49
	.uleb128 0x13
	.uleb128 0x11
	.uleb128 0x1
	.uleb128 0x12
	.uleb128 0x7
	.uleb128 0x40
	.uleb128 0x18
	.uleb128 0x2117
	.uleb128 0x19
	.uleb128 0x1
	.uleb128 0x13
	.byte	0
	.byte	0
	.uleb128 0x21
	.uleb128 0x5
	.byte	0
	.uleb128 0x3
	.uleb128 0xe
	.uleb128 0x3a
	.uleb128 0xb
	.uleb128 0x3b
	.uleb128 0xb
	.uleb128 0x39
	.uleb128 0xb
	.uleb128 0x49
	.uleb128 0x13
	.uleb128 0x2
	.uleb128 0x17
	.uleb128 0x2137
	.uleb128 0x17
	.byte	0
	.byte	0
	.uleb128 0x22
	.uleb128 0x4109
	.byte	0x1
	.uleb128 0x11
	.uleb128 0x1
	.uleb128 0x31
	.uleb128 0x13
	.byte	0
	.byte	0
	.uleb128 0x23
	.uleb128 0x410a
	.byte	0
	.uleb128 0x2
	.uleb128 0x18
	.uleb128 0x2111
	.uleb128 0x18
	.byte	0
	.byte	0
	.uleb128 0x24
	.uleb128 0x2e
	.byte	0
	.uleb128 0x3f
	.uleb128 0x19
	.uleb128 0x3c
	.uleb128 0x19
	.uleb128 0x6e
	.uleb128 0xe
	.uleb128 0x3
	.uleb128 0xe
	.uleb128 0x3a
	.uleb128 0xb
	.uleb128 0x3b
	.uleb128 0x5
	.uleb128 0x39
	.uleb128 0xb
	.byte	0
	.byte	0
	.byte	0
	.section	.debug_loc,"",@progbits
.Ldebug_loc0:
.LVUS0:
	.uleb128 0
	.uleb128 .LVU5
	.uleb128 .LVU5
	.uleb128 0
.LLST0:
	.quad	.LVL0
	.quad	.LVL2
	.value	0x1
	.byte	0x55
	.quad	.LVL2
	.quad	.LFE13
	.value	0x4
	.byte	0xf3
	.uleb128 0x1
	.byte	0x55
	.byte	0x9f
	.quad	0
	.quad	0
.LVUS1:
	.uleb128 0
	.uleb128 .LVU4
	.uleb128 .LVU4
	.uleb128 0
.LLST1:
	.quad	.LVL0
	.quad	.LVL1
	.value	0x1
	.byte	0x54
	.quad	.LVL1
	.quad	.LFE13
	.value	0x4
	.byte	0xf3
	.uleb128 0x1
	.byte	0x54
	.byte	0x9f
	.quad	0
	.quad	0
	.section	.debug_aranges,"",@progbits
	.long	0x2c
	.value	0x2
	.long	.Ldebug_info0
	.byte	0x8
	.byte	0
	.value	0
	.value	0
	.quad	.LFB13
	.quad	.LFE13-.LFB13
	.quad	0
	.quad	0
	.section	.debug_ranges,"",@progbits
.Ldebug_ranges0:
	.quad	.LFB13
	.quad	.LFE13
	.quad	0
	.quad	0
	.section	.debug_line,"",@progbits
.Ldebug_line0:
	.section	.debug_str,"MS",@progbits,1
.LASF99:
	.string	"printf"
.LASF9:
	.string	"__off_t"
.LASF23:
	.string	"_IO_read_ptr"
.LASF35:
	.string	"_chain"
.LASF8:
	.string	"size_t"
.LASF0:
	.string	"__cxx11"
.LASF41:
	.string	"_shortbuf"
.LASF29:
	.string	"_IO_buf_base"
.LASF79:
	.string	"remove"
.LASF16:
	.string	"__mbstate_t"
.LASF44:
	.string	"_codecvt"
.LASF73:
	.string	"fseek"
.LASF5:
	.string	"signed char"
.LASF36:
	.string	"_fileno"
.LASF24:
	.string	"_IO_read_end"
.LASF7:
	.string	"long int"
.LASF22:
	.string	"_flags"
.LASF30:
	.string	"_IO_buf_end"
.LASF39:
	.string	"_cur_column"
.LASF53:
	.string	"_IO_codecvt"
.LASF62:
	.string	"_sys_errlist"
.LASF86:
	.string	"tmpnam"
.LASF38:
	.string	"_old_offset"
.LASF43:
	.string	"_offset"
.LASF92:
	.string	"/home/u0777962"
.LASF80:
	.string	"rename"
.LASF20:
	.string	"_G_fpos_t"
.LASF17:
	.string	"__pos"
.LASF97:
	.string	"_Z3addIiET_S0_S0_"
.LASF52:
	.string	"_IO_marker"
.LASF56:
	.string	"stdin"
.LASF2:
	.string	"unsigned int"
.LASF13:
	.string	"__wchb"
.LASF76:
	.string	"getc"
.LASF1:
	.string	"long unsigned int"
.LASF65:
	.string	"ferror"
.LASF27:
	.string	"_IO_write_ptr"
.LASF59:
	.string	"sys_nerr"
.LASF4:
	.string	"short unsigned int"
.LASF91:
	.string	"addition.cpp"
.LASF31:
	.string	"_IO_save_base"
.LASF12:
	.string	"__wch"
.LASF67:
	.string	"fgetc"
.LASF72:
	.string	"freopen"
.LASF42:
	.string	"_lock"
.LASF37:
	.string	"_flags2"
.LASF49:
	.string	"_mode"
.LASF69:
	.string	"fgets"
.LASF57:
	.string	"stdout"
.LASF90:
	.string	"GNU C++14 8.5.0 20210514 (Red Hat 8.5.0-15) -mtune=generic -march=x86-64 -g -O2"
.LASF77:
	.string	"clearerr"
.LASF28:
	.string	"_IO_write_end"
.LASF87:
	.string	"ungetc"
.LASF96:
	.string	"add<int>"
.LASF95:
	.string	"_IO_lock_t"
.LASF21:
	.string	"_IO_FILE"
.LASF82:
	.string	"setbuf"
.LASF19:
	.string	"__fpos_t"
.LASF70:
	.string	"fopen"
.LASF60:
	.string	"sys_errlist"
.LASF66:
	.string	"fflush"
.LASF34:
	.string	"_markers"
.LASF18:
	.string	"__state"
.LASF3:
	.string	"unsigned char"
.LASF6:
	.string	"short int"
.LASF54:
	.string	"_IO_wide_data"
.LASF61:
	.string	"_sys_nerr"
.LASF40:
	.string	"_vtable_offset"
.LASF51:
	.string	"FILE"
.LASF71:
	.string	"fread"
.LASF14:
	.string	"__count"
.LASF15:
	.string	"__value"
.LASF11:
	.string	"char"
.LASF55:
	.string	"fpos_t"
.LASF74:
	.string	"fsetpos"
.LASF64:
	.string	"feof"
.LASF85:
	.string	"tmpfile"
.LASF63:
	.string	"fclose"
.LASF10:
	.string	"__off64_t"
.LASF25:
	.string	"_IO_read_base"
.LASF33:
	.string	"_IO_save_end"
.LASF84:
	.string	"getchar"
.LASF48:
	.string	"__pad5"
.LASF94:
	.string	"11__mbstate_t"
.LASF50:
	.string	"_unused2"
.LASF58:
	.string	"stderr"
.LASF89:
	.string	"argv"
.LASF47:
	.string	"_freeres_buf"
.LASF32:
	.string	"_IO_backup_base"
.LASF83:
	.string	"setvbuf"
.LASF78:
	.string	"perror"
.LASF88:
	.string	"argc"
.LASF46:
	.string	"_freeres_list"
.LASF81:
	.string	"rewind"
.LASF45:
	.string	"_wide_data"
.LASF75:
	.string	"ftell"
.LASF68:
	.string	"fgetpos"
.LASF98:
	.string	"main"
.LASF26:
	.string	"_IO_write_base"
.LASF93:
	.string	"__gnu_cxx"
	.ident	"GCC: (GNU) 8.5.0 20210514 (Red Hat 8.5.0-15)"
	.section	.note.GNU-stack,"",@progbits
